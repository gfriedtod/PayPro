package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Space;
import com.example.paypromodulith.userManager.domain.model.SpaceDto;
import java.util.LinkedHashSet;

public class SpaceMapper {

  public static Space toEntity(SpaceDto spaceDto) {
    return Space.builder().id(spaceDto.getId()).build();
  }

  public static SpaceDto toDto(Space space) {
    return SpaceDto.builder().id(space.getId()).organisationDtos(new LinkedHashSet<>(space.getOrganisations().stream().map(OrganisationMapper::toDtoWithoutDependencies).toList())).build();
  }
}
