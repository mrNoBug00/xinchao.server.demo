package com.xinchao.payload.response;

import com.xinchao.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class UserResponse {

    private String id;


    private String userName;


    private String email;


    private String phone;
    private String arc;
    private String vnId;
    private String passport;


    private String address;

    private String lineId;

    private String numberZalo;

    private Role role;

    public UserResponse(String id, String userName, String email, String phone, String address, String arc, String vnId, String passport, Role role, String lineId, String numberZalo) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.arc = arc;
        this.vnId = vnId;
        this.passport = passport;
        this.role = role;
        this.lineId = lineId;
        this.numberZalo = numberZalo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
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

    public String getArc() {
        return arc;
    }

    public void setArc(String arc) {
        this.arc = arc;
    }

    public String getVnId() {
        return vnId;
    }

    public void setVnId(String vnId) {
        this.vnId = vnId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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
