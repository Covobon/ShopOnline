package com.smartshop.service;

import com.smartshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Service
public class CurrentUserServiceImp implements CurrentUserService {

    private User user;

    @Autowired
    private UserService userService;

    @Override
    public void update(String username) {
        if (username == null) {
            this.user = null;
        } else {
            this.user = userService.findByUserName(username);
            this.user.setLastAccess(new Date());
            this.user = user;
        }
    }

    @Override
    public User get() {
        return this.user;
    }
}
