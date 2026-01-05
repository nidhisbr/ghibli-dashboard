package com.example.ghibli.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghibli.jwt.JwtTokenService;

@RestController
public class JwtAuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    public JwtAuthController(AuthenticationManager authenticationManager,
                             JwtTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        return tokenService.generateToken(authentication);
    }
}