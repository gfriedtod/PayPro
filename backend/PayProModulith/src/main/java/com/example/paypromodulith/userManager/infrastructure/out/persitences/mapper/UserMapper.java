package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.User;
import com.example.paypromodulith.userManager.domain.model.UserDto;

public class UserMapper {

   public static User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .createdAt(userDto.getCreatedAt())
                .department(DepartmentMapper.toEntity(userDto.getDepartment()))
                .organisation(OrganisationMapper.toEntityWithoutDependencies(userDto.getOrganisation()))
                .displayName(userDto.getDisplayName())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .rule(userDto.getRule())
                .cni(userDto.getCni())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .dateBirth(userDto.getDateBirth())
                .gender(userDto.getGender())
                .image(userDto.getImage())
                .build();
    }

   public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .department(DepartmentMapper.toDto(user.getDepartment()))
//                .organisation(OrganisationMapper.toDto(user.getOrganisation()))
                .displayName(user.getDisplayName())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .rule(user.getRule())
                .cni(user.getCni())
                .phone(user.getPhone())
                .address(user.getAddress())
                .dateBirth(user.getDateBirth())
                .gender(user.getGender())
                .image(user.getImage())
                .build();
    }

    public static User toEntityWithoutDependencies(UserDto user) {
        return User.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .displayName(user.getDisplayName())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .rule(user.getRule())
                .cni(user.getCni())
                .phone(user.getPhone())
                .address(user.getAddress())
                .dateBirth(user.getDateBirth())
                .gender(user.getGender())
                .image(user.getImage())
                .build();
    }
}
