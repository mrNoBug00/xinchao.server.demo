package com.xinchao.payload.response;

import com.xinchao.models.Role;

public class LoginResponse {
    private String token;

    private long expiresIn;

    private String username;
    private String userId;

    private Role role;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }


    public String getUserName() {
        return username;
    }

    public LoginResponse setUserName(String username) {
        this.username = username;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LoginResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }



    public Role getRole() {
        return role;
    }

    public LoginResponse setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                ", username=" + username +
                ", role=" + role +
                ", userId=" + userId +
                '}';
    }
}
