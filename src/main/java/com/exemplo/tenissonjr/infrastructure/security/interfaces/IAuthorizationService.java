package com.exemplo.tenissonjr.infrastructure.security.interfaces;

public interface  IAuthorizationService {
    IUserAuthorization authorize(String userName);
}