package org.example.filter;

import org.example.exceptionHandler.UnAuthorizationException;
import org.junit.jupiter.api.Order;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局過濾器
 * */
@Component
@Order(1)//值越大 優先值越低
public class AuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //例子：請求須攜帶Authorization=admin ,如果無此請求頭,認為未登入或失敗
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //請求頭獲取
        String authorization =request.getHeaders().getFirst("Authorization");
        if(authorization.isEmpty() || !authorization.equals("admin")){
//            //返回401
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //終結請求
//            return response.setComplete();
            //返回自訂格式
            throw new UnAuthorizationException(401,"權限不足");
        }
        //放行並轉發
        return chain.filter(exchange);
    }
}
