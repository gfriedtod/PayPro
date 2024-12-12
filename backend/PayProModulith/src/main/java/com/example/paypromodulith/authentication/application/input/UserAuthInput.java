package com.example.paypromodulith.authentication.application.input;

import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.model.LoginResponse;
import com.example.paypromodulith.authentication.domain.model.LoginUserResponse;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserAuthInput {

    Optional<LoginUserResponse> login(LoginRequest loginRequest) throws NoSuchAlgorithmException;
}
