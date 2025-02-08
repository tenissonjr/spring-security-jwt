package com.exemplo.tenissonjr.infrastructure.security.interfaces;

import java.util.List;

public interface IUserAuthorization {
    List<String> getAauthorities();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}
