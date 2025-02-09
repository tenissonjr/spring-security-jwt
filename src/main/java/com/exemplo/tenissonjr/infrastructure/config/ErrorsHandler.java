package com.exemplo.tenissonjr.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exemplo.tenissonjr.shared.exception.ApplicationLoginException;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(ApplicationLoginException.class )
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleApplicationLoginException(ApplicationLoginException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ApplicationLoginException : " + ex.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: " + ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
    }
    



}
