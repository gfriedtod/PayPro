package com.example.paypromodulith.authentication.application.output;

import com.example.paypromodulith.authentication.domain.model.LoginRequest;
import com.example.paypromodulith.authentication.domain.model.UserDto;

public interface UserOutputPort {
    com.example.paypromodulith.userManager.domain.model.UserDto loadUserByUsername(String username);
   UserDto login(LoginRequest loginRequest);
}
