package com.exemplo.tenissonjr.infrastructure.login.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.infrastructure.login.dto.LoginParamDTO;
import com.exemplo.tenissonjr.infrastructure.security.service.AuthenticationService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value = "/login")
@AllArgsConstructor
public class LoginController {

    private AuthenticationService authenticationService;

    @PostMapping
    public String login(@RequestBody LoginParamDTO loginDTO) {
        return authenticationService.authenticate(loginDTO);
    }

}
