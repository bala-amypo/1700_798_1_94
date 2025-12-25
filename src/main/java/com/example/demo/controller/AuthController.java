package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        CustomUserDetailsService.DemoUser user = userDetailsService.getByEmail(request.getEmail());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            String token = jwtTokenProvider.generateToken(auth, user.getId(), user.getRole(), user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole()));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        CustomUserDetailsService.DemoUser user = userDetailsService.registerUser("User", request.getEmail(), request.getPassword());
        UsernamePasswordAuthenticationToken auth = 
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        String token = jwtTokenProvider.generateToken(auth, user.getId(), user.getRole(), user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole()));
    }
}