package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.AdminDto;
import com.example.paypromodulith.entity.Admin;
import java.util.LinkedHashSet;

public class AdminMapper {

    public static AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .role(RoleMapper.toDto(admin.getRole()))
                .adminRows(new LinkedHashSet<>(admin.getAdminRows().stream().map(AdminRowMapper::toDtoWithoutAdmin).toList()))
                .space(SpaceMapper.toDtoWithoutDependencies(admin.getSpace()))
                .adminDepartments(new LinkedHashSet<>(admin.getAdminDepartments().stream().map(AdminDeppartmentsMapper::toDto).toList()))
                .build();
    }

    public static AdminDto toDtoWithoutDependencies(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .role(RoleMapper.toDto(admin.getRole()))
                .space(SpaceMapper.toDtoWithoutDependencies(admin.getSpace()))
                .adminRows(new LinkedHashSet<>(admin.getAdminRows().stream().map(AdminRowMapper::toDtoWithoutAdmin).toList()))
                .adminDepartments(new LinkedHashSet<>(admin.getAdminDepartments().stream().map(AdminDeppartmentsMapper::toDtoWithoutAdmin).toList()))
                .build();
    }

    public static Admin toEntity(AdminDto adminDto) {
        return Admin.builder()
                .id(adminDto.getId())
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .role(RoleMapper.toEntity(adminDto.getRole()))
                .space(SpaceMapper.toEntity(adminDto.getSpace()))
                .adminDepartments(new LinkedHashSet<>(adminDto.getAdminDepartments().stream().map(AdminDeppartmentsMapper::toEntity).toList()))
                .adminRows(new LinkedHashSet<>(adminDto.getAdminRows().stream().map(AdminRowMapper::toEntity).toList()))
                .build();
    }
}
