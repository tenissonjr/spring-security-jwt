package com.exemplo.tenissonjr.countries.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.infrastructure.login.dto.UsuarioLoginDTO;
import com.exemplo.tenissonjr.infrastructure.login.service.LoginService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {


    private final LoginService   loginDecodeTokenService;

    @GetMapping
    public ResponseEntity<List<String>> listCountries(@AuthenticationPrincipal Jwt jwt) {
        UsuarioLoginDTO usuario = loginDecodeTokenService.decodeToken(jwt);
        return ResponseEntity.ok().body(List.of(usuario.getNome(),usuario.getRamal(),"Brazil", "Argentina", "Uruguay"));
    }    

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('DETAIL_COUNTRY')")  
    public ResponseEntity<String> detailContry() {
        return ResponseEntity.ok().body("Brazil -  Capital : Brasilia");    
    }    

    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE_COUNTRY')")  
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCountry(@AuthenticationPrincipal Jwt jwt) {

        UsuarioLoginDTO usuario = loginDecodeTokenService.decodeToken(jwt);

        return ResponseEntity.ok().body("%s -  Created a new country by ".formatted(usuario.getNome()));     
    }    

    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE_COUNTRY')")    
    public ResponseEntity<Void> deleteCountry() {
        return ResponseEntity.ok().build();
    }    



}