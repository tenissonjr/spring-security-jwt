package com.exemplo.tenissonjr.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encripter { public static void main(String[] args) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String rawPassword = "123";
    String encodedPassword = passwordEncoder.encode(rawPassword);
    System.out.println("Encoded password: " + encodedPassword);
}
}