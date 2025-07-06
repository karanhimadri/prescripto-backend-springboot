package com.example.prescripto.dto;

public class PanelLoginRequestDto {
    private String email;
    private String password;
    private String authority;

    public PanelLoginRequestDto() {}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
