package com.ApiClima.security.controller;

import com.ApiClima.security.jwt.AuthCredentials;
import com.ApiClima.security.jwt.TokenUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentials credentials) {
        return ResponseEntity.ok().build();
    }
}
