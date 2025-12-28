package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final Map<String, DemoUser> users = new HashMap<>();

    public CustomUserDetailsService() {
        users.put("admin@city.com", new DemoUser(1L, "Admin", "admin@city.com", "admin123", "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DemoUser user = users.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }

    public DemoUser getByEmail(String email) {
        return users.get(email);
    }

    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists with email: " + email);
        }
        DemoUser user = new DemoUser((long) (users.size() + 1), name, email, password, "USER");
        users.put(email, user);
        return user;
    }

    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;

        public DemoUser(Long id, String name, String email, String password, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
        }
        public Long getId() {
          return id;
           }
        public String getName() {
         return name;
          }
        public String getEmail() {
         return email;
          }
        public String getPassword() {
         return password;
          }
        public String getRole() {
         return role; 
         }
    }
}