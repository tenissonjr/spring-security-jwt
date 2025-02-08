package com.exemplo.tenissonjr.infrastructure.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.infrastructure.login.dto.LoginParamDTO;
import com.exemplo.tenissonjr.infrastructure.login.dto.UsuarioLoginDTO;
import com.exemplo.tenissonjr.infrastructure.login.service.LoginService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(value = "/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UsuarioLoginDTO> login(@RequestBody LoginParamDTO loginDTO) {

        UsuarioLoginDTO usuario = loginService.authenticate(loginDTO.getPonto(), loginDTO.getSenha());

        return ResponseEntity.ok().body(usuario);
    }

}
