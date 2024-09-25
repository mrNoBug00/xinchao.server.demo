package com.xinchao.payload.request;

import com.xinchao.models.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String vnId;
    private String arc;
    private String passport;
    private String email;
    private String phone;
    private String address;
    private String lineId;
    private String numberZalo;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVnId() {
        return vnId;
    }

    public void setVnId(String vnId) {
        this.vnId = vnId;
    }

    public String getArc() {
        return arc;
    }

    public void setArc(String arc) {
        this.arc = arc;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getNumberZalo() {
        return numberZalo;
    }

    public void setNumberZalo(String numberZalo) {
        this.numberZalo = numberZalo;
    }
}
