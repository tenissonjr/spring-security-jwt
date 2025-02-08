package com.exemplo.tenissonjr.infrastructure.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthenticationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthorizationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthenticated;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ExternalApiAuthenticationProvider implements AuthenticationProvider {

    private final  IAuthenticationService externalAuthService;
    private final  IAuthorizationService externalAuthorizationService;

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Chama a API externa para autenticar o usuário
        IUserAuthenticated userAuthenticated = externalAuthService.authenticate(username, password);
        if (userAuthenticated!=null) {
            List<String> authorities = externalAuthorizationService.getAuthorities(username);

            List<SimpleGrantedAuthority> grantedAuthorities= authorities == null ? 
                            new ArrayList<>() 
                            : authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    grantedAuthorities) ;

            auth.setDetails(userAuthenticated);

            return auth;                    
        } else {
            throw new AuthenticationException("Invalid credentials") {
            };
        }

    }
    

    public Authentication _authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    // Chama a API externa para autenticar o usuário
    IUserAuthenticated userAuthenticated = externalAuthService.authenticate(username, password);
    if (userAuthenticated != null) {
        List<String> authorities = externalAuthorizationService.getAuthorities(username);

        List<SimpleGrantedAuthority> grantedAuthorities = authorities == null ? 
                        new ArrayList<>() 
                        : authorities.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userAuthenticated, // Define o principal como userAuthenticated
                password,
                grantedAuthorities);

        auth.setDetails(userAuthenticated);

        return auth;                    
    } else {
        throw new AuthenticationException("Invalid credentials") {
        };
    }
}

}
