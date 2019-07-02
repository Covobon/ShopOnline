package com.smartshop.api;

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
    @PutMapping
    public void createUser(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userService.deleteByUserName(userName);
    }
}
