package com.example.paypromodulith.authentication.application.output;

import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.model.LoginResponse;
import com.example.paypromodulith.authentication.domain.model.LoginUserResponse;
import com.example.paypromodulith.authentication.domain.model.UserDto;

import java.util.Optional;

public interface UserOutputPort {
    UserDto loadUserByUsername(String username);
   UserDto login(LoginRequest loginRequest);
}
