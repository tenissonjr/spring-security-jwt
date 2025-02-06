package com.exemplo.tenissonjr.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.dto.LoginDTO;
import com.exemplo.tenissonjr.repository.JwtService;

@Service
public class AuthenticationService {


    private AuthenticationManager authenticationManager;

    private JwtService jwtService;


    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public String authenticate(LoginDTO loginDTO) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);

        return jwtService.generateToken(auth);
    }

}