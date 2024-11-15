package com.example.paypromodulith.authentication.infracsturcture.out.adapter;

import com.example.paypromodulith.authentication.application.output.AdminOutputPort;
import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.authentication.domain.model.SignupResponse;
import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.AdminAuthRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class AdminAuthPersitenceAdapter implements AdminOutputPort {

    private final AdminAuthRepository adminAuthRepository;
    private final ModelMapper modelMapper;
    @Override
    public AdminDto loadUserByUsername(String email) {
        return modelMapper.map(adminAuthRepository.findByEmail(email), AdminDto.class);
    }

    @Override
    public SignupResponse signup(AdminDto adminDto) {
        AdminDto admin =this.modelMapper.map(adminAuthRepository.save(modelMapper.map(adminDto, Admin.class)),AdminDto.class);
        return SignupResponse.builder().adminDto(admin).build();
    }
}
