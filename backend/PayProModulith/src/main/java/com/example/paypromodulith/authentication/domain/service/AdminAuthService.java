package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.input.AdminAuthInput;
import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.domain.model.*;
import com.example.paypromodulith.authentication.infracsturcture.out.jwt.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminAuthService implements AdminAuthInput {

    private final AdminOutputPort adminOutputPort;
    private final PasswordEncoder passwordEncoder;

    public Optional<LoginResponse> login(LoginRequest request) throws NoSuchAlgorithmException {
        AdminDto admin = adminOutputPort.loadUserByUsername(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
             String token = JwtHelper.generateToken(new TokenDetails(admin.getId(), admin.getName()));
            return Optional.ofNullable(LoginResponse.builder().user(admin).token(token).build());
        }
        return Optional.empty();
    }



    @Override
    public SignupResponse signup(AdminDto adminDto) {
        var password = adminDto.getPassword();
        adminDto.setPassword(passwordEncoder.encode(password));
        return adminOutputPort.signup(adminDto);
    }
}
