package com.smartshop.configuration.security;

import com.smartshop.model.User;
import com.smartshop.model.UserRole;
import com.smartshop.repositories.UserRepository;
import com.smartshop.repositories.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);

        if (user == null) {
            log.error("User not found! " + username);
            throw new UsernameNotFoundException("User" + username + " was not found in the database");
        }

        log.info("Found user: " + username);

        List<UserRole> userRoles = userRoleRepository.findByUserName(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantList);
        return userDetails;
    }
}
