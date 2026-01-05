package com.example.ghibli.service;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ghibli.model.User;
import com.example.ghibli.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

   
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    
    public User validateLogin(String email, String rawPassword) {
    	
    	Optional<User> userOpt = userRepository.findByEmail(email);
    	if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }
    	
    	User user = userOpt.get();

        // 🔐 BCrypt password check
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    	
    }
    
    
}