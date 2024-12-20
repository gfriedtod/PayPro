package com.example.paypromodulith.authentication.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link com.example.paypromodulith.entity.Space} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpaceDto implements Serializable {
  private UUID id;
  private OffsetDateTime createdAt;
  private Set<AdminDto> admins = new LinkedHashSet<>();
  private Set<OrganisationDto> organisationDtos = new LinkedHashSet<>();
}
