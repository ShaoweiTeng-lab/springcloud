package org.example.dao.pojo;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private  Long userId;
    private  String  title;
    private  Long price;
    private  Integer num;
    private  User user;
}
