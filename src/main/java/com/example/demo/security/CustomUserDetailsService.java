package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, DemoUser> users = new HashMap<>();
    
    public CustomUserDetailsService() {
        // Initialize with some demo users
        users.put("admin@city.com", new DemoUser(1L, "Admin User", "admin@city.com", 
                "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVyaUi", "ADMIN"));
        users.put("user@city.com", new DemoUser(2L, "Regular User", "user@city.com", 
                "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVyaUi", "USER"));
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DemoUser demoUser = users.get(email);
        if (demoUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + demoUser.getRole()));
        
        return new User(demoUser.getEmail(), demoUser.getPassword(), authorities);
    }
    
    public DemoUser getByEmail(String email) {
        DemoUser user = users.get(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
    
    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        Long newId = (long) (users.size() + 1);
        DemoUser newUser = new DemoUser(newId, name, email, 
                "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVyaUi", "USER");
        users.put(email, newUser);
        return newUser;
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
        
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }
}