package com.smartshop.api;

import com.smartshop.dao.user.*;
import com.smartshop.service.user.UserService;
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
    //TODO-------
    @PostMapping()
    @PutMapping
    public void createUser(@RequestBody User user){
        userService.save(user);
    }
    //TODO----

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userService.deleteByUserName(userName);
    }
}
