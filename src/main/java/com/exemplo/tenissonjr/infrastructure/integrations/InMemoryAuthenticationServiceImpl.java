package com.exemplo.tenissonjr.infrastructure.integrations;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthenticationService;
import com.exemplo.tenissonjr.infrastructure.security.interfaces.IUserAuthenticated;


@Service
public class InMemoryAuthenticationServiceImpl  implements IAuthenticationService {

    class IUserAuthenticatedImpl implements IUserAuthenticated {
        private final String nome;
        private final String ramal;

        public IUserAuthenticatedImpl(String nome,String ramal) {
            this.nome = nome;
            this.ramal = ramal;

        }

        @Override
        public String getNome() {
            return nome;
        }

        @Override
        public String getRamal() {
            return ramal;
        }
    }

    Map<String, IUserAuthenticated> users = Map.of("P_8056", new IUserAuthenticatedImpl("Diretora Lu ","6-3700"),
                                            "P_5242", new IUserAuthenticatedImpl("Servidor João","6-3903")  ,
                                            "P_0000", new IUserAuthenticatedImpl("Usuário de consulta","6-3903")  
                                            );

    @Override
    public IUserAuthenticated authenticate(String username, String password) {
        if (password.equals("123") && users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }

}
