package com.example.paypromodulith.authentication.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class LoginResponse {
    private String token;
    private AdminDto user;
}
