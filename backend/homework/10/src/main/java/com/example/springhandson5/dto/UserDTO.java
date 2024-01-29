package com.example.springhandson5.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.stereotype.Component;



public class UserDTO {
    private String userName;
    private String fullName;
    @JsonIgnore
    private String password;
    private String email;
    private String role;

    public UserDTO(String userName, String fullName, String password, String email, String role) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
