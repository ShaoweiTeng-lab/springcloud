package org.example.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientInterceptorConfig  implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("set header");
        requestTemplate.header("Authorization","bear 123123asdad");
    }
}
