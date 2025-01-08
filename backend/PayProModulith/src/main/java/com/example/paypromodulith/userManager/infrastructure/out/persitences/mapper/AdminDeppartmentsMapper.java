package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.AdminDepartment;
import com.example.paypromodulith.userManager.domain.model.AdminDepartmentDto;

public class AdminDeppartmentsMapper{

  static AdminDepartmentDto toDto(AdminDepartment adminDepartment) {
    return AdminDepartmentDto.builder()
        .id(adminDepartment.getId())
        .createdAt(adminDepartment.getCreatedAt())
        .admin(AdminMapper.toDtoWithoutDependencies(adminDepartment.getAdmin()))
        .department(DepartmentMapper.toDto(adminDepartment.getDepartment()))
        .build();
  }

  static AdminDepartmentDto toDtoWithoutAdmin(AdminDepartment adminDepartment) {
    return AdminDepartmentDto.builder()
            .id(adminDepartment.getId())
            .createdAt(adminDepartment.getCreatedAt())
//            .admin(AdminMapper.toDtoWithoutDependencies(adminDepartment.getAdmin()))
            .department(DepartmentMapper.toDto(adminDepartment.getDepartment()))
            .build();
  }
  static AdminDepartment toEntity(AdminDepartmentDto adminDepartmentDto) {
    return AdminDepartment.builder()
        .id(adminDepartmentDto.getId())
        .createdAt(adminDepartmentDto.getCreatedAt())
        .admin(AdminMapper.toEntity(adminDepartmentDto.getAdmin()))
        .department(DepartmentMapper.toEntity(adminDepartmentDto.getDepartment()))
        .build();
  }

}
