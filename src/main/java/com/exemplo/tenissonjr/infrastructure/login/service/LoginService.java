package com.exemplo.tenissonjr.infrastructure.login.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.login.dto.UsuarioLoginDTO;
import com.exemplo.tenissonjr.infrastructure.security.dto.AuthenticationDTO;
import com.exemplo.tenissonjr.infrastructure.security.model.CustomUserDetails;
import com.exemplo.tenissonjr.infrastructure.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {


    private final AuthenticationService authenticationService;

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

    public UsuarioLoginDTO authenticate(String userName, String password) {

        AuthenticationDTO authenticationDTO = authenticationService.authenticate(userName, password);

        Authentication authentication = authenticationDTO.authentication();
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();        


        List<String> authorities =authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            return UsuarioLoginDTO.builder()
                .ponto(userDetails.getUsername())
                .nome(userDetails.getUser().getNome())
                .ramal(userDetails.getUser().getRamal())
                .authorities(authorities)
                .token(authenticationDTO.token())
                .tokenExpirationTime(authenticationDTO.expiresAT().toEpochMilli())
                .build();
    }  

        

}
