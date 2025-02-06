package com.exemplo.tenissonjr.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.dto.LoginDTO;
import com.exemplo.tenissonjr.service.AuthenticationService;


@RestController
public class AuthenticationController {
    
    private AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public String authenticate(LoginDTO loginDTO) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword());
        return authenticationService.authenticate(authentication);


    }
    

}
