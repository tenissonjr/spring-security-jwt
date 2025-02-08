package com.exemplo.tenissonjr.infrastructure.integrations;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthenticationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthenticated;


@Service
public class IAuthenticationServiceImpl  implements IAuthenticationService {

    class IUserAuthenticatedImpl implements IUserAuthenticated {
        private String username;

        public IUserAuthenticatedImpl(String username) {
            this.username = username;
        }

        @Override
        public String getUserName() {
            return username;
        }
    }

    Map<String, IUserAuthenticated> users = Map.of("P_8056", new IUserAuthenticatedImpl("Fabiane")
                                            , "P_6677", new IUserAuthenticatedImpl("Tenisson")  );

    @Override
    public IUserAuthenticated authenticate(String username, String password) {
        if (password.equals("123") && users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

}
