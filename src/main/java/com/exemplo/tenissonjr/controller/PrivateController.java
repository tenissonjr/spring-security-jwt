package com.exemplo.tenissonjr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private") 
public class PrivateController {
    
    @GetMapping("/private")
    public String getMessage() {
        return "==>>   Private endpoint";
    }
}