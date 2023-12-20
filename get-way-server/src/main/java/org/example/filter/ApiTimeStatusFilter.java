package org.example.filter;

import org.junit.jupiter.api.Order;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 統計每個接口的時長過濾器
 * */
@Component
@Order(2)
public class ApiTimeStatusFilter implements GlobalFilter {
    public  static final  Logger log= LoggerFactory.getLogger(ApiTimeStatusFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //在轉發請求前 獲取時間
        long start =System.currentTimeMillis();
        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            //獲取Response 的執行時間
            long end =System.currentTimeMillis();
            long spend= end-start;
            log.info("路徑："+ exchange.getRequest().getURI().getPath() +" 花費時間： " + spend );
            System.out.println("路徑："+ exchange.getRequest().getURI().getPath());
            System.out.println("花費時間： "+ spend);
        }));
    }
}