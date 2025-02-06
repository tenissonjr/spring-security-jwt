package com.exemplo.tenissonjr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public class User {
    
    @Id
    private String userName;
    private String password;
    
    public User() {
    }
    
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }
    
    public String getUsername() {
        return userName;
    }
    
    public void setUsername(String username) {
        this.userName = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
