package com.smartshop.service;

import com.smartshop.dao.user.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User getByUserName();
    public void save(User user);
    public void deleteByUserName(String userName);
}
