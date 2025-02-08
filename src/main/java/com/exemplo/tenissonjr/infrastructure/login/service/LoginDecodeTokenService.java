package com.exemplo.tenissonjr.infrastructure.login.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.login.dto.UsuarioLoginDTO;

@Service
public class LoginDecodeTokenService {

    public UsuarioLoginDTO decodeToken(Jwt jwt) {

        String token = jwt.getTokenValue();
        String username = jwt.getClaimAsString("nome");
        String ramal = jwt.getClaimAsString("ramal");        
        
        return UsuarioLoginDTO.builder()
                .ponto(username)
                .nome(username)
                .ramal(ramal)
                .token(token)
                .tokenExpirationTime(jwt.getExpiresAt().toEpochMilli())
                .build();
    }

}
