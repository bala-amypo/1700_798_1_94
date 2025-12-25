package com.example.demo.service.impl;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final CustomUserDetailsService userDetailsService;

    public UserServiceImpl(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public CustomUserDetailsService.DemoUser registerUser(String name, String email, String password) {
        return userDetailsService.registerUser(name, email, password);
    }

    @Override
    public CustomUserDetailsService.DemoUser getUserByEmail(String email) {
        return userDetailsService.getByEmail(email);
    }
}