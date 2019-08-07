package com.smartshop.api;

import com.smartshop.model.Orders;
import com.smartshop.service.CurrentUserService;
import com.smartshop.service.OrderService;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping
    public List<Orders> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/{userName}")
    public List<Orders> findByUserName(@PathVariable String userName) {
        return orderService.findByUserName(userName);
    }

    @GetMapping("/history")
    public List<Orders> findByUserName() {
        return orderService.findByUserName(currentUserService.get().getUserName());
    }

/*    @GetMapping("/myorders")
    public List<Orders>*///TODO
}
