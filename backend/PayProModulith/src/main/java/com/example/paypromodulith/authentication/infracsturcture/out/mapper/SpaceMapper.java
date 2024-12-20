package com.example.paypromodulith.authentication.infracsturcture.out.mapper;

import com.example.paypromodulith.authentication.domain.model.SpaceDto;
import com.example.paypromodulith.entity.Space;
import java.util.LinkedHashSet;

public class SpaceMapper {

  public static Space toEntity(SpaceDto spaceDto) {
    return Space.builder().id(spaceDto.getId()).build();
  }

  public static SpaceDto toDto(Space space) {
    return SpaceDto.builder().id(space.getId()).organisationDtos(new LinkedHashSet<>(space.getOrganisations().stream().map(OrganisationMapper::toDtoWithoutDependencies).toList())).build();
  }

    public static SpaceDto toDtoWithoutDependencies(Space space) {
        return SpaceDto.builder().id(space.getId()).build();
    }
}
