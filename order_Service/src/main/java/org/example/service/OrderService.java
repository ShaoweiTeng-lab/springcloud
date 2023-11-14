package org.example.service;



import org.apache.hc.core5.http.ParseException;
import org.example.dao.pojo.Order;

import java.io.IOException;

public interface OrderService {
    Order getById(Long id) throws IOException, ParseException;
}
