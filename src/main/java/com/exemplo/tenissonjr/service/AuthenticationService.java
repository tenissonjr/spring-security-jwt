package com.exemplo.tenissonjr.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.repository.JwtService;

@Service
public class AuthenticationService {

    private JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}