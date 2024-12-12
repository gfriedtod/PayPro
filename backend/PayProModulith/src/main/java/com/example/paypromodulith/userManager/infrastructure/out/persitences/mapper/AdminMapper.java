package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.userManager.domain.model.AdminDto;

import java.util.LinkedHashSet;

public class AdminMapper {

    public static AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .role(admin.getRole())
                .space(SpaceMapper.toDto(admin.getSpace()))
                .adminDepartments(admin.getAdminDepartments())
                .build();
    }

    public static Admin toEntity(AdminDto adminDto) {
        return Admin.builder()
                .id(adminDto.getId())
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .role(adminDto.getRole())
                .space(SpaceMapper.toEntity(adminDto.getSpace()))
                .adminDepartments(adminDto.getAdminDepartments())
                .adminRows(new LinkedHashSet<>(adminDto.getAdminRows().stream().map(AdminRowMapper::toEntity).toList()))
                .build();
    }
}
