package org.example.controller;



import org.apache.hc.core5.http.ParseException;
import org.example.dao.pojo.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/{id}")
    public Order findById(@PathVariable(value = "id") Long id) throws IOException, ParseException {
        Order order=orderService.getById(id) ;
        //todo 根據userId查用戶(遠程調用)
        return  order;
    }
}
