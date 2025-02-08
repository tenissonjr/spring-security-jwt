package com.exemplo.tenissonjr.infrastructure.security.dto;

import java.time.Instant;

import org.springframework.security.core.Authentication;

public record AuthenticationDTO(Authentication authentication, String token,Instant expiresAT) {}
