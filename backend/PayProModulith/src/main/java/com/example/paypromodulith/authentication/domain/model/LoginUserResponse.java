package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.userManager.domain.model.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class 
LoginUserResponse {
    private String token;
    private UserDto user;
}
