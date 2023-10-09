package org.example.controller;



import org.apache.hc.core5.http.ParseException;
import org.example.dao.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") Long id) {
        User user=userService.getById(id) ;
        //todo 根據userId查用戶(遠程調用)
        return  user;
    }
}
