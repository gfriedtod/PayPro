package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.File;
import com.example.paypromodulith.userManager.domain.model.FileDto;

public class FileMapper {
    public static File toEntity(FileDto fileDto) {
        return File.builder()
                .id(fileDto.getId())
                .name(fileDto.getName())
                .dateFile(fileDto.getDateFile())
                .link(fileDto.getLink())
                .user(UserMapper.toEntityWithoutDependencies(fileDto.getUser()))
                .organisation(OrganisationMapper.toEntityWithoutDependencies(fileDto.getOrganisation()))
                .build();
    }

    public static FileDto toDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .dateFile(file.getDateFile())
                .link(file.getLink())
                .build();
    }
}
