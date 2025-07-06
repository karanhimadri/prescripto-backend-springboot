package com.example.prescripto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDto {
    private Boolean status;
    private String message;
    private String username;
    private String role;
    private String token;

    public AuthResponseDto() {};
    public AuthResponseDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public AuthResponseDto(Boolean status, String message, String username, String role, String token) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public Boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) { this.message = message; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
