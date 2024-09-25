package com.xinchao.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String arc;
    private String vnId;
    private String passport;
    private String phone;

    private String password;

    public String getEmail() {
        return email;
    }

    public LoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getArc() {
        return arc;
    }

    public LoginDto setArc(String arc) {
        this.arc = arc;
        return this;
    }

    public String getVnId() {
        return vnId;
    }

    public LoginDto setVnId(String vnId) {
        this.vnId = vnId;
        return this;
    }

    public String getPassport() {
        return passport;
    }

    public LoginDto setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public LoginDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}