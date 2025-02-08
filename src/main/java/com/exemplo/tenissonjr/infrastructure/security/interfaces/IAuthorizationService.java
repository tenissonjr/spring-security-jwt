package com.exemplo.tenissonjr.infrastructure.security.interfaces;

import java.util.List;

public interface  IAuthorizationService {
    List<String> getAuthorities(String userName);
}