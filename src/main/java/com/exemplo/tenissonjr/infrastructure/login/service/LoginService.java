package com.exemplo.tenissonjr.infrastructure.login.service;

import java.util.List;

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
        
        return new UsuarioLoginDTO(username, username, ramal, List.of(), token, jwt.getExpiresAt().toEpochMilli());

    }

    public UsuarioLoginDTO authenticate(String userName, String password) {

        AuthenticationDTO authenticationDTO = authenticationService.authenticate(userName, password);
        
        CustomUserDetails userDetails = (CustomUserDetails) authenticationDTO.authentication().getPrincipal();        
        
        List<String> authorities =authenticationDTO.authentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            return new UsuarioLoginDTO(userDetails.getUsername(), 
                                        userDetails.getUser().getNome(), 
                                        userDetails.getUser().getRamal(), 
                                        authorities, authenticationDTO.token(), 
                                        authenticationDTO.expiresAT().toEpochMilli()
                );

    }  

        

}
