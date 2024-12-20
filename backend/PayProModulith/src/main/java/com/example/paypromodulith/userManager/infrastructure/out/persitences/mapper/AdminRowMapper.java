package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.AdminRow;
import com.example.paypromodulith.userManager.domain.model.AdminRowDto;

public class AdminRowMapper {

    public static AdminRow toEntity(AdminRowDto adminRowDto) {
        return AdminRow.builder()
//                .createdAt(adminRowDto.getCreatedAt())
                .admin(AdminMapper.toEntityWithoutDependencies(adminRowDto.getAdmin()))
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
