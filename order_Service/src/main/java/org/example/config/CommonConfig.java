package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {
    @Bean
    public RestTemplate restTemplate(){
        //return  new RestTemplate();//默認JDK 的URL Connection
//        return  new RestTemplate(new HttpComponentsClientHttpRequestFactory());//切成HttpClient
        return  new RestTemplate(new OkHttp3ClientHttpRequestFactory());//切成oKHttpClient
    }
}
