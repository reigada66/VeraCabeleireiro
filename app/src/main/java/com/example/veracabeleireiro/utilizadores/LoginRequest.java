package com.example.veracabeleireiro.utilizadores;
public class LoginRequest {
    private String username;
    private String password;
    private String csrfToken;

    public LoginRequest(String username, String password, String csrfToken) {
        this.username = username;
        this.password = password;
        this.csrfToken = csrfToken;
    }

    // Getters and setters for the fields
}
