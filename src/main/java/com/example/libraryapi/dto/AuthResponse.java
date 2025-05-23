package com.example.libraryapi.dto;

public class AuthResponse {
    private String token;

    private AuthResponse() {}

    private AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
