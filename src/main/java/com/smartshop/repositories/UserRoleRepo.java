package com.smartshop.repositories;

import com.smartshop.model.UserRole;
import com.smartshop.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
}
