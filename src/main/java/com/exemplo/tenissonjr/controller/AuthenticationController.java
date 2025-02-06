package com.exemplo.tenissonjr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.dto.LoginDTO;
import com.exemplo.tenissonjr.service.AuthenticationService;


@RestController
public class AuthenticationController {
    
    private AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authenticationService.authenticate(loginDTO);
    }

}
