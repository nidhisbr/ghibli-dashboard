package com.example.ghibli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;

import com.example.ghibli.dto.LoginRequest;
import com.example.ghibli.dto.SignupRequest;
import com.example.ghibli.jwt.JwtTokenService;
import com.example.ghibli.model.User;
import com.example.ghibli.repository.UserRepository;
import com.example.ghibli.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    public AuthController(AuthService authService, UserRepository userRepository,JwtTokenService jwtTokenService,AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

//    basic auth
//    @PostMapping("/login")
//    public ResponseEntity<Void> login(Authentication authentication) {
//        // If we reach here, credentials are valid
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {

        // 1️⃣ Authenticate manually (because at login, Authentication is null)
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2️⃣ Generate JWT token
        String token = jwtTokenService.generateToken(authentication);

        // 3️⃣ Get user name from DB to send to frontend
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 4️⃣ Return token + name in JSON
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("name", user.getName());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("User registered successfully");
    }



}

