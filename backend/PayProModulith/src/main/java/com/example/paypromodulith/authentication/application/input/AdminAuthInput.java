package com.example.paypromodulith.authentication.application.input;

import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.SignupResponse;

public interface AdminAuthInput {
    SignupResponse signup(AdminDto adminDto);
}
