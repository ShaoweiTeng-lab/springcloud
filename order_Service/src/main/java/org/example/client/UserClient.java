package org.example.client;

import org.example.dao.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = 註冊中心名稱
//path = 對應controller 訪問路徑（有點像requestMapping寫在controller 上）

@FeignClient(name="user-service",path="user")
public interface UserClient {
    @GetMapping("{id}")
    public User findById(@PathVariable(value = "id") Long id);
}
