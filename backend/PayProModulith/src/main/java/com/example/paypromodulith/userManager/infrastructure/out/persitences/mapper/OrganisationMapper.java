package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class OrganisationMapper {

    public static Organisation toEntity(OrganisationDto organisationDto) {
        return Organisation.builder()
                .id(organisationDto.getId())
                .name(organisationDto.getName())
                .space(SpaceMapper.toEntity(organisationDto.getSpace()))
                .createdAt(organisationDto.getCreatedAt())
                .adminRows(new LinkedHashSet<>(organisationDto.getAdminRows().stream().map(AdminRowMapper::toEntity).toList()))
                .build();
    }

    public  static Organisation toEntityWithoutAdminRows(OrganisationDto organisationDto) {
        return Organisation.builder()
                .id(organisationDto.getId())
                .name(organisationDto.getName())
                .adminRows(new HashSet<>())
                .departments(new LinkedHashSet<>(organisationDto.getDepartments().stream().map(DepartmentMapper::toEntity).toList()))
                .files(new LinkedHashSet<>(organisationDto.getFiles().stream().map(FileMapper::toEntity).toList()))
                .build();
    }

    public  static  Organisation toEntityWithoutDependencies(OrganisationDto organisationDto) {
        return Organisation.builder()
                .id(organisationDto.getId())
                .name(organisationDto.getName())
                .adminRows(new HashSet<>())
                .departments(new HashSet<>())
                .files(new HashSet<>())
                .build();
    }
    public static OrganisationDto toDto(Organisation organisation) {
        return OrganisationDto.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .adminRows(new LinkedHashSet<>(organisation.getAdminRows().stream().map(AdminRowMapper::toDtoWithoutOrganisation).toList()))
                .departments(new LinkedHashSet<>(organisation.getDepartments().stream().map(DepartmentMapper::toDto).toList()))
                .files(new LinkedHashSet<>(organisation.getFiles().stream().map(FileMapper::toDto).toList()))
                .build();
    }

    public static OrganisationDto toDtoWithoutDependencies(Organisation organisation) {
        System.out.println(organisation.getName());
        return OrganisationDto.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .name(organisation.getName())
                .createdAt(organisation.getCreatedAt())
                .build();
    }
}
