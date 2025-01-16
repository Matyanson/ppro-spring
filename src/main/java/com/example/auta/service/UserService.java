package com.example.auta.service;

import com.example.auta.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User findById(Long id);
    void save(User user);

}
