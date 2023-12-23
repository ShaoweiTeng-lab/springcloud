package org.example.exceptionHandler;

import lombok.Data;

@Data
public class UnAuthorizationException  extends  RuntimeException{
    private  Integer status;//狀態碼
    public  UnAuthorizationException(Integer status, String message){
        super(message);
        this.status=status;
    }
}
