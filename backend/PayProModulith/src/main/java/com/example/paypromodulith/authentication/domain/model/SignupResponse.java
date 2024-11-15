package com.example.paypromodulith.authentication.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private String message;
    private AdminDto adminDto;
}
