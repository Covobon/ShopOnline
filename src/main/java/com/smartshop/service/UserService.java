package com.smartshop.service.store;

import com.smartshop.model.User;
import com.smartshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeByUserName(String userName){
        userRepository.deleteById(userName);
    }

    
}
