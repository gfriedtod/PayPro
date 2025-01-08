package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.AdminRowUseCase;
import com.example.paypromodulith.userManager.application.out.AdminRowOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminRowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRowService implements AdminRowUseCase {

    private final AdminRowOutputPort adminRowOutputPort;


    @Override
    public AdminRowDto create(AdminRowDto adminDto) {
        return adminRowOutputPort.create(adminDto);
    }
}
