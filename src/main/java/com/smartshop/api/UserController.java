package com.smartshop.api;

import com.smartshop.model.User;
import com.smartshop.service.UserService;
import com.smartshop.utils.EncrytedPasswordUtils;
import org.hibernate.annotations.Parameter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String username = user.getUserName();
        String password = user.getPassword();

        if (userService.verifyAccount(username, password)) {
            User theUser = userService.findByUserName(username);
            if (theUser.getLastAccess() == theUser.getCreateTime()){
                //TODO
            }else{
                return ResponseEntity.ok().body(theUser);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/verify")
    public ResponseEntity verify(@RequestParam String authenticate, String y) {
        byte[] decodeBytes = Base64.getDecoder().decode(authenticate);
        String decodeString = new String(decodeBytes);


        Date dateNow = new Date();

        byte[] decodeDateByte = Base64.getDecoder().decode(y);
        String decodeDate = new String(decodeDateByte);
        Long miliseconds = Long.parseLong(decodeDate);

        Date theDate = new Date(miliseconds);

        if (dateNow.compareTo(theDate) > 0){
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        }

        System.out.println(dateNow);
        int index = 0;
        for (int i = 0; i < decodeString.length(); i++) {
            if (decodeString.charAt(i) == ':') {
                index = i;
                break;
            }
        }

        String username = decodeString.substring(0, index);
        String password = decodeString.substring(index + 1, decodeString.length());

        if (userService.verifyAccount(username, password)) {
            User theUser = userService.findByUserName(username);

            if (theUser.getCreateTime() == theUser.getLastAccess()){
                theUser.setLastAccess(dateNow);
                return ResponseEntity.ok().body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
