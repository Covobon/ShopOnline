package com.smartshop.service;

import com.smartshop.model.User;
import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRepository;
import com.smartshop.repositories.UserRoleRepository;
import com.smartshop.utils.EncrytedPasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findById(userName).orElse(null);
    }

    public void add(User user){
        Date date = new Date();
        user.setCreateTime(date);

        if(user.getUserRoles().size() == 0) {
            userRoleRepository.save(new UserRole(user.getUserName(), "USER"));
        }
        user.setPassword(encrytedPasswordUtils.encrytePassword(user.getPassword()));

        userRepository.save(user);
    }

    public void deleteByUserName(String userName){
        userRoleRepository.deleteByUserName(userName);
        userRepository.deleteById(userName);
    }
}
