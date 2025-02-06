package com.exemplo.tenissonjr.info.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    
    @GetMapping("/about")    
    public String getMessage() {
        return "==>>   Info Controller   <<==";
    }

    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        return ResponseEntity.ok(LocalDateTime.now().toString());
    }

}