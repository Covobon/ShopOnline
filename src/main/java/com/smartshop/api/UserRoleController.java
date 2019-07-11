package com.smartshop.api;

import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRoleRepository;
import com.smartshop.service.UserRoleService;
import com.smartshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@RestController
@RequestMapping("/api/userrole")

public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping()
    public List<UserRole> findAll(@RequestParam Map<String, String> allParams){
        Stack<String> arrayKey = new Stack<>();
        Stack<String> arrayValue = new Stack<>();

        if (allParams == null) {
            return userRoleService.findAll();
        }

        for (Map.Entry<String, String> entry : allParams.entrySet()){
            arrayKey.push(entry.getKey());
            arrayKey.push(entry.getValue());
        }

        logg

        return null);
    }

    @DeleteMapping()
    public void deleteByUserNameAndRole(@RequestParam String userName, @RequestParam String role) {
        userRoleService.deleteByUserNameAndRole(userName, role);
    }

}
