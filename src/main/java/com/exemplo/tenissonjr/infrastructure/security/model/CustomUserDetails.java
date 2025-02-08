package com.exemplo.tenissonjr.infrastructure.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthenticated;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthorization;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final transient IUserAuthenticated user;
    private final transient IUserAuthorization userAuthorization   ;

    public CustomUserDetails(String username, String password, IUserAuthenticated user, IUserAuthorization userAuthorization ) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.userAuthorization = userAuthorization;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            List<String> authorities = userAuthorization.getAauthorities();

            return authorities == null ? 
                            new ArrayList<>() 
                            : authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList();


    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return userAuthorization.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userAuthorization.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userAuthorization.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userAuthorization.isEnabled();
    }

    public IUserAuthenticated getUser() {
        return user;
    }
}