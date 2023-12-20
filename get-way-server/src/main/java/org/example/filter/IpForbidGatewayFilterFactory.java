package org.example.filter;

import org.junit.jupiter.api.Order;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
/**
 * 指定 ip 才可放行 進入的ip 符合permitIP
 * */
@Component
@Order(3)
public class IpForbidGatewayFilterFactory  extends AbstractGatewayFilterFactory<IpForbidGatewayFilterFactory.Config> {
    public  IpForbidGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("permitIP");
    }
    /**
     * 過濾邏輯
     * */
    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            //獲請request 的 ip 地址
            String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            System.out.println(hostAddress);
            //是否與參數設定的一樣
            if(config.getPermitIP().equals(hostAddress))
                return  chain.filter(exchange);//放行
            else {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return  exchange.getResponse().setComplete();
            }

        });
    }

    /**
     * 靜態內部類別 用於傳遞參數
     * */
    public  static class Config{
        private  String permitIP;

        public String getPermitIP() {
            return permitIP;
        }

        public void setPermitIP(String flag) {
            this.permitIP = flag;
        }
    }
}
