package com.smartshop.api;

import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRoleRepository;
import com.smartshop.service.UserRoleService;
import com.smartshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/userrole")

public class UserRoleController {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping()
    public List<UserRole> find(@RequestParam Map<String, String> allParams){
        Queue<String> queueKey = new LinkedList<String>();
        Queue<String> queueValue = new LinkedList<String>();

        if (allParams.isEmpty()) {
            return userRoleService.findAll();
        }

        for (Map.Entry<String, String> entry : allParams.entrySet()){
            queueKey.add(entry.getKey());
            queueValue.add(entry.getValue());
        }
        return userRoleService.findByUser(queueValue.remove());
    }

    @DeleteMapping()
    public void delete(@RequestParam Map<String, String> allParams) {
        Queue<String> queueKey = new LinkedList<String>();
        Queue<String> queueValue = new LinkedList<String>();

        for (Map.Entry<String, String> entry : allParams.entrySet()){
            queueKey.add(entry.getKey());
            queueValue.add(entry.getValue());
        }

        if (queueValue.size() == 1) {
            userRoleService.deleteByUser(queueValue.remove());
        }
        if (queueValue.size() == 2) {
            userRoleService.deleteByUserNameAndRole(queueValue.remove(), queueValue.remove());
        }
    }

}
