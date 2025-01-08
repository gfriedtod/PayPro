package com.example.paypromodulith.authentication.domain.service;

import com.example.paypromodulith.authentication.application.input.UserAuthInput;
import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import com.example.paypromodulith.authentication.domain.model.*;
import com.example.paypromodulith.authentication.infracsturcture.out.jwt.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserAuthService implements UserAuthInput {

    private final UserOutputPort userOutputPort;
    private final PasswordEncoder passwordEncoder;


    public Optional<LoginUserResponse> login(LoginRequest request) throws NoSuchAlgorithmException {
        var user = userOutputPort.loadUserByUsername(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = JwtHelper.generateToken(new TokenDetails(user.getId(), user.getEmail()));
            return Optional.ofNullable(LoginUserResponse.builder().user(user).token(token).build());
        }
        return Optional.empty();
    }

}
