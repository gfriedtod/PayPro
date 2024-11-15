package com.example.paypromodulith.authentication.domain.model;

import lombok.Data;

@Data
public class LoginRequest {
   private String email;
   private String password;
}
