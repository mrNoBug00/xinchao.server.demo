package com.xinchao.payload.request;

public class LoginRequest {
    private String identifier;
    private String password;

    // Getters and Setters

    public String getIdentifier() {
        return identifier ;
    }

    public void setIdentifier(String username) {
        this.identifier  = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
