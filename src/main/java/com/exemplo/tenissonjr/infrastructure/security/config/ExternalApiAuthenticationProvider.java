package com.exemplo.tenissonjr.infrastructure.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthenticationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthorizationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthenticated;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthorization;
import com.exemplo.tenissonjr.infrastructure.security.model.CustomUserDetails;
import com.exemplo.tenissonjr.shared.exception.ApplicationAuthorizationException;
import com.exemplo.tenissonjr.shared.exception.ApplicationLoginException;


@Component
public class ExternalApiAuthenticationProvider implements AuthenticationProvider {

    private final IAuthenticationService externalAuthService;
    private final IAuthorizationService externalAuthorizationService;

    public ExternalApiAuthenticationProvider(IAuthenticationService externalAuthService, IAuthorizationService externalAuthorizationService) {
        this.externalAuthService = externalAuthService;
        this.externalAuthorizationService = externalAuthorizationService;
    }

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
        if (userAuthenticated == null) {
            throw new ApplicationLoginException("Invalid credentials");
        }
        // Chama a API externa para obter as autorizações do usuário
        IUserAuthorization userAuthorization = externalAuthorizationService.authorize(username);
        if (userAuthorization == null) {
            throw new ApplicationAuthorizationException("Authorization not found");
        }

        // Cria um CustomUserDetails com informações personalizadas
        CustomUserDetails userDetails = new CustomUserDetails(username, password, userAuthenticated,userAuthorization);

        // Retorna um objeto de autenticação válido
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities());

    }

}
