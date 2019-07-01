package com.smartshop.repo.user;

import java.util.List;

public interface UserDao {
    public List<User> getUsers();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(String userName);
}
