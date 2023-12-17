package org.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import feign.Retryer;

@Configuration
public class CommonConfig {
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        //return  new RestTemplate();//默認JDK 的URL Connection
////        return  new RestTemplate(new HttpComponentsClientHttpRequestFactory());//切成HttpClient
//        return  new RestTemplate(new OkHttp3ClientHttpRequestFactory());//切成oKHttpClient
//    }


}
