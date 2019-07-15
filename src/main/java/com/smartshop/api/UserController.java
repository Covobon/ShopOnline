package com.smartshop.api;

import com.smartshop.model.User;
import com.smartshop.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public List<User> getUser(){
        return userService.findAll();
    }

    @PostMapping()
    public void create(@RequestBody User user){
        userService.add(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping()
    public void deleteUser(@RequestParam String userName){
        userService.deleteByUserName(userName);
    }
}
