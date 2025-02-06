package com.exemplo.tenissonjr.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.login.dto.LoginParamDTO;

@Service
public class AuthenticationService {


    private AuthenticationManager authenticationManager;

    private JwtService jwtService;


    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public String authenticate(LoginParamDTO loginDTO) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getPonto(), loginDTO.getSenha());
        var auth = authenticationManager.authenticate(userNamePassword);

        return jwtService.generateToken(auth);
    }

}