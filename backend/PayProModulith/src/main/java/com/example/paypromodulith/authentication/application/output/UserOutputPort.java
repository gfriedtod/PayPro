package com.example.paypromodulith.authentication.application.output;

import com.example.paypromodulith.authentication.domain.model.UserDto;

public interface UserOutputPort {
    UserDto loadUserByUsername(String username);
}
