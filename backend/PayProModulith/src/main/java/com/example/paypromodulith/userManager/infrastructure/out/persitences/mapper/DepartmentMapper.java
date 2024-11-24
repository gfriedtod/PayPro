package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Department;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;

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
