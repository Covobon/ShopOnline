package com.smartshop.dao.user;

import java.util.List;

public interface UserRoleDao {
    public List<UserRole> findAll();
    public List<UserRole> findByUserName(String userName);
    public void add(UserRole userRole);
    public void delete(UserRole userRole);
    public void deleteByUser(User user);
}
