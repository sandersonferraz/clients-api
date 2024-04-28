package com.dev.san.controller;

import com.dev.san.model.entity.auth.AuthenticationDto;
import com.dev.san.model.entity.auth.RegisterDto;
import com.dev.san.service.auth.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data) {
        return ResponseEntity.ok(this.loginService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto data) {
        this.loginService.register(data);
        return ResponseEntity.ok().build();
    }
}
