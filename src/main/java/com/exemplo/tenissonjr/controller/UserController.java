package com.exemplo.tenissonjr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.tenissonjr.model.User;


@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('SUPER_USER')")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = List.of(new User("User 1", "123"), new User("User 2", "456"));
        return ResponseEntity.ok().body(users);

    }    

}