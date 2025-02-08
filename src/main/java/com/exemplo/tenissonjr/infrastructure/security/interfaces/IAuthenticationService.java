package com.exemplo.tenissonjr.infrastructure.security.interfaces;

public interface IAuthenticationService {
    IUserAuthenticated authenticate(String username, String password);
}
