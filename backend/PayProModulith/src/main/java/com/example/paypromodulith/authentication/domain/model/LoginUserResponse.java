package com.example.paypromodulith.authentication.domain.model;

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
