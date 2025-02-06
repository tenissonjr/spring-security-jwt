package com.exemplo.tenissonjr.repository;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    
    private final JwtEncoder encoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.encoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {

        Instant now = Instant.now(); 
        long expiration = 3600L; // 1 Minuto

        String scopes =authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var claims = JwtClaimsSet.builder()
                .issuer("com.exemplo.tenissonjr")
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .claim("authorities", scopes)
                .build();        

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
   
}