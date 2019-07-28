package com.smartshop.service;

import com.smartshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CurrentUserServiceImp implements CurrentUserService {

    private User user;

    @Autowired
    private UserService userService;

    @Override
    public void update(String username) {
        this.user = userService.findByUserName(username);
        this.user.setLastAccess(new Date());
        userService.save(user);
    }

    @Override
    public User get() {
        return this.user;
    }
}
