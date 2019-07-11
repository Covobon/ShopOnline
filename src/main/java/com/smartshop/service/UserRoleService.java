package com.smartshop.service;

import com.smartshop.model.User;
import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRole> findAll(){
        return userRoleRepository.findAll();
    }

    public List<UserRole> findByUser(User user){
        String userName = user.getUserName();
        return userRoleRepository.findByUserName(userName);
    }

    public void deleteByUser(User user) {
        String userName = user.getUserName();
        userRoleRepository.deleteByUserName(userName);
    }
}
