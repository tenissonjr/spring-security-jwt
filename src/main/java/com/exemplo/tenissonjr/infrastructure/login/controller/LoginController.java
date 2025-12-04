package com.exemplo.tenissonjr.infrastructure.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.infrastructure.login.dto.LoginParamDTO;
import com.exemplo.tenissonjr.infrastructure.login.dto.UsuarioLoginDTO;
import com.exemplo.tenissonjr.infrastructure.login.service.LoginService;


@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;
    
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<UsuarioLoginDTO> login(@RequestBody LoginParamDTO loginDTO) {

        UsuarioLoginDTO usuario = loginService.authenticate(loginDTO.ponto(), loginDTO.senha());

        return ResponseEntity.ok().body(usuario);
    }

}
