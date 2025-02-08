package com.exemplo.tenissonjr.infrastructure.security.service;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.model.CustomUserDetails;

@Service
public class JwtService {
    
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public JwtService(JwtEncoder jwtEncoder,JwtDecoder jwtDecoder) {
        this.encoder = jwtEncoder;
        this.decoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication) {

        Instant now = Instant.now(); 
        long expiration = 60 * 10L; // 10 Minutos

        //Obter usedetails do authentication
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();        


        String scopes =authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var claims = JwtClaimsSet.builder()
                .issuer("com.exemplo.tenissonjr")
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .claim("authorities", scopes)
                .claim("nome", userDetails.getUser().getNome())
                .claim("ramal", userDetails.getUser().getRamal())
                .build();        

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    //Função para decodificar o token que recebe Authentication como parametro
    public Map<String, Object> decodeToken(Authentication authentication) {
        return decoder.decode(authentication.getCredentials().toString()).getClaims();
    }
   
}