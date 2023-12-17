package org.example.service.imp;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.example.client.UserClient;
import org.example.dao.mapper.OrderMapper;
import org.example.dao.pojo.Order;
import org.example.dao.pojo.User;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    //@Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; //從註冊中心服務端 拉取 服務訊息的class

    @Autowired
    private UserClient userClient;
    @Override
    public Order getById(Long id) throws IOException, ParseException {
        Order order=orderMapper.getById(id);
//        order.setUser(httpGetUser(order.getUserId()));
//        order.setUser(restGetUser(order.getUserId()));
//        order.setUser(discoveryGetUser(order.getUserId()));
//        order.setUser(discoveryGetUserLoadBalanced(order.getUserId()));
        order.setUser(openFenginGetUser(order.getUserId()));
        return  order ;
    }
    /**
     *方式一 使用httpClient實現遠程調用
     * */
    private User httpGetUser(Long userId) throws IOException, ParseException{
        //方式一 使用httpClient實現遠程調用
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //創建get請求
        String url ="http://localhost:9001/user/"+userId;
        HttpGet http =new HttpGet(url);
        //發出請求
        CloseableHttpResponse response= closeableHttpClient.execute(http);

        //拿到返回內容
        HttpEntity entity= response.getEntity();

        String rsJson= EntityUtils.toString(entity);
        ObjectMapper objectMapper =new ObjectMapper();
        User user = objectMapper.readValue(rsJson,User.class);
        return  user;
    }
    /**
     *方式二 使用restTemplete實現遠程調用
     * */
    private  User restGetUser(Long userId){
        String url ="http://localhost:9001/user/"+userId;
        User user =restTemplate.getForObject(url, User.class);//發請求並轉json
        return  user;
    }

    /**
     *方式三 使用discovery+restTemplete實現遠程調用
     * */
    private  User discoveryGetUser(Long userId){
        discoveryClient.getServices().forEach(x-> System.out.println(x));
        List<ServiceInstance> serviceInstanceList=discoveryClient.getInstances("user-Service");
        //因為只有一個 所以先拿出第一個 (若是集群則用負載均衡)
        ServiceInstance  serviceInstance =serviceInstanceList.get(0);
        String host =serviceInstance.getHost();
        int port =serviceInstance.getPort();
        String url ="http://"+host+":"+port+"/user/"+userId;
        User user =restTemplate.getForObject(url, User.class);//發請求並轉json
        return  user;
    }
    /**
     *方式四 使用discovery+restTemplete＋loadBalanced實現遠程調用
     * */
    private  User discoveryGetUserLoadBalanced(Long userId){
        //服務名
        String url ="http://USER-SERVICE"+"/user/"+userId;
        User user =restTemplate.getForObject(url, User.class);//發請求並轉json
        return  user;

    }
    /**
     * openFeign 遠程調用
     * */

    private  User openFenginGetUser(Long userId){
       User user = userClient.findById(userId);
       return  user;
    }
}
