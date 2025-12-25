package com.example.demo.service;

import com.example.demo.security.CustomUserDetailsService.DemoUser;

public interface UserService {
    DemoUser registerUser(String name, String email, String password);
    DemoUser getUserByEmail(String email);
}