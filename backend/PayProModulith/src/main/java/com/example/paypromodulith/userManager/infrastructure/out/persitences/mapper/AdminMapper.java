package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.userManager.domain.model.AdminDto;

public class AdminMapper {

    static AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .build();
    }

    public static Admin toEntity(AdminDto adminDto) {
        return Admin.builder()
                .id(adminDto.getId())
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .build();
    }
}
