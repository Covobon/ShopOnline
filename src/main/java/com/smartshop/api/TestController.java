package com.smartshop.api;

import com.smartshop.model.Orders;
import com.smartshop.service.CurrentUserService;
import com.smartshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String test() {
        orderService.save(new Orders("admin", "Processing", ""));
        return "Success!";
    }
}
