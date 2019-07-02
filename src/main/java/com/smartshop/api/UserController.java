package com.smartshop.api;

import com.smartshop.dao.user.User;
import com.smartshop.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public List<User> getUser(){
        return userDao.getUsers();
    }

    @PostMapping("/user")
    @ResponseBody
    public String createUser(@RequestBody User user){
        userDao.addUser(user);
        return "Success";
    }
}
