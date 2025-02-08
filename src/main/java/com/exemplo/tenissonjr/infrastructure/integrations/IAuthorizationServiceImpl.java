package com.exemplo.tenissonjr.infrastructure.integrations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exemplo.tenissonjr.infrastructure.security.interfaces.IAuthorizationService;

@Service
public class IAuthorizationServiceImpl implements IAuthorizationService {

    @Override
    public List<String> getAuthorities(String userName) {
        return switch (userName) {
            case "P_8056" -> List.of("GESTOR");
            case "P_6678" -> List.of("PESQUISAR_TIPOS_CAPITULO");
            default -> List.of();
        };
    }


}