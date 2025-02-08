package com.exemplo.tenissonjr.infrastructure.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.dto.AuthenticationDTO;

@Service
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public AuthenticationDTO authenticate(String userName, String password) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authenticationManager.authenticate(userNamePassword);

        return new AuthenticationDTO(authentication, jwtService.generateToken(authentication)) ;
    }


}