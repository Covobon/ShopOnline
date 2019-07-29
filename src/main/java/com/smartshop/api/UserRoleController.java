/*
package com.smartshop.api;

import com.smartshop.model.UserRole;
import com.smartshop.dao.user.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userrole")

public class UserRoleController {
    //TODO
    @Autowired
    private UserRoleDao userRoleDao;

    @GetMapping()
    public List<UserRole> findAll(){
        return userRoleDao.findAll();
    }

    @GetMapping("/{userName}")
    public List<UserRole> findByUserName(@PathVariable("userName") String userName){
        System.out.println(userName);
        return userRoleDao.findByUserName(userName);
    }
}
*/
