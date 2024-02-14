package com.ApiClima.security.jwt;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
