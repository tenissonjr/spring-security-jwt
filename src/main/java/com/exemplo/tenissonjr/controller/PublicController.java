package com.exemplo.tenissonjr.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    
    @GetMapping("/hello")    
    public String getMessage() {
        return "==>>   Hello word !" ;
    }

    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        return ResponseEntity.ok(LocalDateTime.now().toString());
    }

}