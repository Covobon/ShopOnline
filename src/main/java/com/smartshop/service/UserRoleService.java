package com.smartshop.service;

import com.smartshop.model.User;
import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRole> findAll(){
        return userRoleRepository.findAll();
    }

    public List<UserRole> findByUser(String userName){
        return userRoleRepository.findByUserName(userName);
    }

    public void deleteByUser(User user) {
        String userName = user.getUserName();
        userRoleRepository.deleteByUserName(userName);
    }

    public void deleteByUserNameAndRole(String userName, String role) {
        userRoleRepository.deleteByUserNameAndRole(userName, role);
    }

    public void deleteByUser(String userName) {
        userRoleRepository.deleteByUserName(userName);
    }
}
