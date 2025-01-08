package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.AdminDepartmentDto;
import com.example.paypromodulith.entity.AdminDepartment;

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
