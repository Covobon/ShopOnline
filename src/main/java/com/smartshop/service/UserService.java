package com.smartshop.service;

import com.smartshop.model.User;
import com.smartshop.repositories.UserRepository;
import com.smartshop.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findById(userName).orElse(null);

    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteByUserName(String userName){
        userRoleRepository.deleteByUserName(userName);
        userRepository.deleteById(userName);
    }
}
