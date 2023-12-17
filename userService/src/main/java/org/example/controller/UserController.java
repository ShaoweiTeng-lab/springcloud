package org.example.controller;



import org.apache.hc.core5.http.ParseException;
import org.example.dao.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") Long id,
                         @RequestParam("name")String name,
                         @RequestHeader("Authorization") String authName) {
        User user=userService.getById(id) ;
        System.out.println("授權訊息：" +authName);
        System.out.println("參數name : "+ name);
        //todo 根據userId查用戶(遠程調用)
        return  user;
    }
}
