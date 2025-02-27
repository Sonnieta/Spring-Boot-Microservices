package com.Service.Students.Dto;

import java.util.List;

public class UserResponse {
    private String username;
    private String email;
    private List<String> roles;  // Store roles as a List of Strings

    public UserResponse(String username, String email, List<String> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
