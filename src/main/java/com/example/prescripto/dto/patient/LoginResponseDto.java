package com.example.prescripto.dto.patient;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {
    private Boolean status;
    private String message;
    private String email;
    private String name;
    private String token;

    public LoginResponseDto() {}

    public LoginResponseDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public LoginResponseDto(Boolean status, String message, String email, String name, String token) {
        this.status = status;
        this.message = message;
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
