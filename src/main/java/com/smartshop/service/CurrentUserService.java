package com.smartshop.service;

import com.smartshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CurrentUserService {

    void update(String username);

    User get();

}
