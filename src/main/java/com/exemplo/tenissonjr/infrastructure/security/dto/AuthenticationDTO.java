package com.exemplo.tenissonjr.infrastructure.security.dto;

import org.springframework.security.core.Authentication;

public record AuthenticationDTO(Authentication authentication, String token) {}
