package org.example.exceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
@Order(-1)
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    public ObjectMapper objectMapper =new ObjectMapper();
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        //ex 是拋出的異常
        ServerHttpRequest request =exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        Integer code = 0;
        String message="";
        if(ex instanceof  UnAuthorizationException){//處理未授權的異常
            UnAuthorizationException unAuthorizationException =(UnAuthorizationException)ex;
            code = unAuthorizationException.getStatus();
            message=unAuthorizationException.getMessage();
        }
        else {
            code=500;
            message="服務器異常";
        }
        //寫回json 給前端
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ResultResponse rs =new ResultResponse<>();
        rs.setCode(code);
        rs.setMessage(message);

        return  response.writeWith(Mono.fromSupplier(()->{
                DataBufferFactory factory= response.bufferFactory();
                byte[] bytes=null;
            try {
                bytes= objectMapper.writeValueAsBytes(rs);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return  factory.wrap(bytes);
        }));
    }
}
