package com.example.paypromodulith.authentication.application.output;

import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.SignupResponse;

public interface AdminOutputPort {


    AdminDto loadUserByUsername(String email);
    SignupResponse signup(AdminDto adminDto);
}
