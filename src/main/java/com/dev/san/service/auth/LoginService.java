package com.dev.san.service.auth;

import com.dev.san.excption.BadRequestException;
import com.dev.san.infra.security.TokenService;
import com.dev.san.model.entity.auth.AuthenticationDto;
import com.dev.san.model.entity.auth.LoginResponseDto;
import com.dev.san.model.entity.auth.RegisterDto;
import com.dev.san.model.entity.auth.Users;
import com.dev.san.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private  final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginResponseDto login(AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token =  this.tokenService.generateToken( (Users) auth.getPrincipal());
        return new LoginResponseDto(token);
    }

    public void register(RegisterDto data) {
        if (Objects.isNull(data))
            throw new BadRequestException("{msg.register.is.null}");
        Users user = new Users(data.login(), new BCryptPasswordEncoder().encode(data.password()), data.role());
        this.userRepository.save(user);
    }
}
