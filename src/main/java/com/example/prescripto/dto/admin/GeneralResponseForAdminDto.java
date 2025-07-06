package com.example.prescripto.dto.admin;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponseForAdminDto {

    private Boolean status;
    private String message;
    private String username;
    private String email;

    public GeneralResponseForAdminDto() {};

    public GeneralResponseForAdminDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
    public GeneralResponseForAdminDto(Boolean status, String message, String username, String email) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
