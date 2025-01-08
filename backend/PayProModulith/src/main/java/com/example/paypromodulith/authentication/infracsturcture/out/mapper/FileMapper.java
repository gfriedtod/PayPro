package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.FileDto;
import com.example.paypromodulith.entity.File;

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
