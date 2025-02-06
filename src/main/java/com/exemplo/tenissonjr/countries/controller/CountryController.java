package com.exemplo.tenissonjr.countries.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @GetMapping
    public ResponseEntity<List<String>> listCountrue() {
        return ResponseEntity.ok().body(List.of("Brazil", "Argentina", "Uruguay"));
    }    

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_COUNTRY')")    
    public ResponseEntity<Void> createCountry() {
        return ResponseEntity.ok().build();
    }    

    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE_COUNTRY')")    
    public ResponseEntity<Void> deleteCountry() {
        return ResponseEntity.ok().build();
    }    



}