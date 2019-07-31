package com.smartshop.service;

import com.smartshop.model.Order;
import com.smartshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order){
        orderRepository.customSave(order.getAddress(), order.getCreateTime(), order.getStatus(), order.getUserName());
    }
}
