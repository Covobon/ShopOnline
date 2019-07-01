package com.smartshop.repo.user;

import java.util.List;

public interface UserRoleDao {
    public void createUserRole(UserRole userRole);
    public List<String> findAllByUserName(String userName);
    public void removeUserRoleById();

}
