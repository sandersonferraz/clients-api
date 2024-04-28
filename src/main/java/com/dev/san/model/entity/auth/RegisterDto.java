package com.dev.san.model.entity.auth;

public record RegisterDto(String login, String password, UserRole role) {
}
