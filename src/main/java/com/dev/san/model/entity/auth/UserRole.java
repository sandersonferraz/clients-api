package com.dev.san.model.entity.auth;

public enum UserRole {
    ADMIN("admin"),
    USER("user");


    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
