package com.smartshop.api;

import com.smartshop.model.User;
import com.smartshop.service.UserService;
import com.smartshop.utils.EncrytedPasswordUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    @CrossOrigin(origins = {"*"})
    public List<User> getUser(){
        return userService.findAll();
    }

    @PostMapping
    public void create(@RequestBody User user){
        userService.add(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam String userName){
        userService.deleteByUserName(userName);
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:4200")

    public ResponseEntity<User> authenticate(@RequestBody User user){
        User theUser =  userService.findByUserName(user.getUserName());
        if (theUser == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (BCrypt.checkpw(user.getPassword(), theUser.getPassword())) {
            theUser.setPassword(user.getPassword());
            return new ResponseEntity<User>(theUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
