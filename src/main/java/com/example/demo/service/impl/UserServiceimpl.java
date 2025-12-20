package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection ONLY (TestNG compatible)
    public UserServiceimpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String fullName, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(
                fullName,
                email,
                encodedPassword,
                "USER"   // default role
        );

        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    public boolean exists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
