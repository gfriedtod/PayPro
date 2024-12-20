package com.example.paypromodulith.authentication.infracsturcture.out.adapter;

import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.SignupResponse;
import com.example.paypromodulith.authentication.infracsturcture.out.mapper.AdminMapper;
import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.AdminAuthRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AdminAuthPersitenceAdapter implements AdminOutputPort {

    private final AdminAuthRepository adminAuthRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public AdminDto loadUserByUsername(String email) {
        var admin = adminAuthRepository.findByEmail(email);
        return admin.map(AdminMapper::toDto).orElse(null);
    }

    @Transactional
    @Override
    public SignupResponse signup(AdminDto adminDto) {

        return SignupResponse.builder().adminDto(AdminMapper.toDtoWithoutDependencies(adminAuthRepository.save(AdminMapper.toEntity(adminDto)))).build();
    }
}
