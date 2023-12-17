package org.example.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RbacAuthRoutePredicateFactory  extends AbstractRoutePredicateFactory<RbacAuthRoutePredicateFactory.Config> {
    public  RbacAuthRoutePredicateFactory(){
        super(Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList("flag");//config 類的屬性名
    }
    /**
     * 編寫斷言規則
     * */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        return (GatewayPredicate) serverWebExchange -> {
            if(config.getFlag().equals("rbac")){
                //獲得請求路徑
                ServerHttpRequest request =serverWebExchange.getRequest();
                String path = request.getURI().getPath();
                if(path.startsWith("/user")||
                        path.startsWith("/role")||
                        path.startsWith("/permission") ||
                        path.startsWith("/menu")
                )//代表符合斷言 轉發
                    return  true;

            }//不符合斷言
            return  false;
        };
    }

    /**
     * 定義Config 靜態內部類別 ，用於接受配置傳遞參數
     * */
    public  static class Config{
        private  String flag;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
