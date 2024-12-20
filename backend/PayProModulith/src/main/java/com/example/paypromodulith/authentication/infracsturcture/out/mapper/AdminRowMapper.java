package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.AdminRowDto;
import com.example.paypromodulith.entity.AdminRow;

public class AdminRowMapper {

    public static AdminRow toEntity(AdminRowDto adminRowDto) {
        return AdminRow.builder()
                .id(adminRowDto.getId())
                .createdAt(adminRowDto.getCreatedAt())
                .admin(AdminMapper.toEntity(adminRowDto.getAdmin()))
                .organisation(OrganisationMapper.toEntityWithoutDependencies(adminRowDto.getOrganisation()))
                .build();
    }

    public static AdminRow toEntityWithoutOrganisation(AdminRowDto adminRowDto) {
        return AdminRow.builder()
                .id(adminRowDto.getId())
                .createdAt(adminRowDto.getCreatedAt())
                .admin(AdminMapper.toEntity(adminRowDto.getAdmin()))
                // Ne pas inclure l'organisation pour éviter la récursion
                .build();
    }

    public static  AdminRowDto toDtoWithoutOrganisation(AdminRow adminRow) {
        return AdminRowDto.builder()
                .id(adminRow.getId())
                .createdAt(adminRow.getCreatedAt())
                .admin(AdminMapper.toDto(adminRow.getAdmin()))
                // Ne pas inclure l'organisation pour éviter la récursion
                .build();
    }

    public static  AdminRowDto toDtoWithoutAdmin(AdminRow adminRow) {
        return AdminRowDto.builder()
                .id(adminRow.getId())
                .createdAt(adminRow.getCreatedAt())
                .organisation(OrganisationMapper.toDtoWithoutDependencies(adminRow.getOrganisation()))

//                .admin(AdminMapper.toDto(adminRow.getAdmin()))
                // Ne pas inclure l'organisation pour éviter la récursion
                .build();
    }


    public static AdminRowDto toDto(AdminRow adminRow) {
        return AdminRowDto.builder()
                .id(adminRow.getId())
                .createdAt(adminRow.getCreatedAt())
                .admin(AdminMapper.toDto(adminRow.getAdmin()))
                .organisation(OrganisationMapper.toDto(adminRow.getOrganisation()))
                .build();
    }

}
