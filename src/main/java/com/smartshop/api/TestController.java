package com.smartshop.api;

import com.smartshop.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping
    public String test() {
        return currentUserService.get().toString();
    }
}
