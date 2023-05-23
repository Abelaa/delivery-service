package com.sa.delivery.service;

import com.sa.delivery.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order getOrderById(String id) {
        Order order = new Order("1", "Taco John", "abelteferaalene@gmail.com");
        return order;
    }

}
