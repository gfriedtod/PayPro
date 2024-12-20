package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.DepartmentDto;
import com.example.paypromodulith.entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .createdAt(departmentDto.getCreatedAt())
                .name(departmentDto.getName())
                .organisation(OrganisationMapper.toEntityWithoutDependencies(departmentDto.getOrganisation()))
                .build();
    }

    public static DepartmentDto toDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .createdAt(department.getCreatedAt())
                .name(department.getName())
                .organisation(OrganisationMapper.toDtoWithoutDependencies(department.getOrganisation()))
                .build();
    }
    public static DepartmentDto toDtoWithoutOrganisation(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .createdAt(department.getCreatedAt())
                .name(department.getName())
                .build();
    }

    public static Department toEntityWithoutOrganisation(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .createdAt(departmentDto.getCreatedAt())
                .name(departmentDto.getName())
                .build();
    }
}
