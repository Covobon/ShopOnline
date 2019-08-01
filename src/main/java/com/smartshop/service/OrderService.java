package com.smartshop.service;

import com.smartshop.model.Orders;
import com.smartshop.model.Product;
import com.smartshop.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public void save(Orders orders){
        ordersRepository.save(orders);
    }

    public void addProduct(int ordersId, Product product) {
        Product theProduct = product;
        int theOrder = ordersId;
        ordersRepository.addProduct(ordersId, product.getProductId(), product.getAmount());
    }

    public Orders findByUserNameAndCreateTime(String userName, Date createTime){
        return ordersRepository.findByUserNameAndCreateTime(userName, createTime);
    };
}
