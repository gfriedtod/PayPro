package com.example.paypromodulith.authentication.application.input;

import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.model.LoginResponse;
import com.example.paypromodulith.authentication.domain.model.SignupResponse;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface AdminAuthInput {
    SignupResponse signup(AdminDto adminDto);
    Optional<LoginResponse> login(LoginRequest request) throws NoSuchAlgorithmException;
}
