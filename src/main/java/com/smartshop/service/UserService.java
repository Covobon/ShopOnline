package com.smartshop.service;

import com.smartshop.model.Role;
import com.smartshop.model.User;
import com.smartshop.repositories.UserRepository;
import com.smartshop.utils.EncrytedPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findById(userName).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean verifyAccount(String username, String password){
        User theUser =  userRepository.findById(username).orElse(null);
        if (theUser == null) {
            return false;
        }

        if (BCrypt.checkpw(password , theUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public void create(User user){
        Date date = new Date();
        user.setCreateTime(date);
        user.setLastAccess(date);
        user.setPassword(encrytedPasswordUtils.encrytePassword(user.getPassword()));
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role(1, "USER"));
        user.setRoles(roles);
        userRepository.save(user);
        log.info("Create user:" + user.toString());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updatePassword(User user) {
        user.setPassword(encrytedPasswordUtils.encrytePassword(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteByUserName(String userName){
        userRepository.deleteById(userName);
    }
}
