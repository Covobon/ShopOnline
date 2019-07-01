package com.smartshop.api;

import com.smartshop.repo.user.User;
import com.smartshop.repo.user.UserDao;
import com.smartshop.repo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping()
    public List<User> getUser(){
        return userRepo.findAll();
    }

    @PostMapping()
    @PutMapping()
    public String createUser(@RequestBody User user){
        userRepo.save(user);
        return "Success";
    }

    @DeleteMapping("/{userName}")
    public String deleteUser(@PathVariable("userName") String userName){
        userRepo.deleteById(userName);
        return "Success";
    }
}
