package com.smartshop.repositories;

import com.smartshop.model.UserRole;
import com.smartshop.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserName(String userName);
    void deleteByUserName(String userName);
    void deleteByUserNameAndRole(String userName, String role);
}
