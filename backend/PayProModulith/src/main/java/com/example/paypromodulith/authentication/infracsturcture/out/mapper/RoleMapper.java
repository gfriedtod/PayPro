package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.entity.Role;
import com.example.paypromodulith.authentication.domain.model.RoleDto;

public class RoleMapper {

    public static Role toEntity(RoleDto roleDto) {
        return Role.builder().id(roleDto.getId()).name(roleDto.getName()).build();

    }

    public static RoleDto toDto(Role role) {
        return RoleDto.builder().id(role.getId()).name(role.getName()).build();
        }
}
