package com.smartshop.service.user;

import com.smartshop.dao.user.User;
import com.smartshop.dao.user.UserRepository;
import com.smartshop.dao.user.UserRoleDao;
import com.smartshop.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findById(userName).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteByUserName(String userName) {
        User user = getByUserName(userName);
        userRoleDao.deleteByUser(user);
        userRepository.deleteById(userName);
    }
}
