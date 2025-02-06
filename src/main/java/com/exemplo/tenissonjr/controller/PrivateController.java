package com.exemplo.tenissonjr.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    
    @GetMapping("/private")
    public String getMessage() {
        return "==>>   Private endpoint";
    }


    @GetMapping("/tipos-capitulos")
    @PreAuthorize("hasAuthority('PESQUISAR_TIPOS_CAPITULO')")
    public String listarTiposCapitulo() {
        return "==>>   tipos-capitulos";
    }    


}