package com.exemplo.tenissonjr.infrastructure.integrations;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthorizationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthorization;

@Service
public class InMemoryAuthorizationServiceImpl implements IAuthorizationService {

    private class UserAuthorization implements IUserAuthorization {

        private final List<String> authorities;

        public UserAuthorization(List<String> authorities) {
            this.authorities = authorities;
        }

        @Override
        public List<String> getAauthorities() {
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

    private final Map<String, UserAuthorization> userAuthorizations = Map.of(
        "P_8056", new UserAuthorization(List.of("CREATE_COUNTRY", "DELETE_COUNTRY", "DETAIL_COUNTRY")),
        "P_5242", new UserAuthorization(List.of("DETAIL_COUNTRY")),
        "P_0000", new UserAuthorization(List.of("BASIC"))
        );

    @Override
    public IUserAuthorization authorize(String userName) {
        return userAuthorizations.get(userName);
    }



}