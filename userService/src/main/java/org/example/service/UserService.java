package org.example.service;



import org.apache.hc.core5.http.ParseException;
import org.example.dao.pojo.Order;
import org.example.dao.pojo.User;

import java.io.IOException;

public interface UserService {
    User getById(Long id) ;
}
