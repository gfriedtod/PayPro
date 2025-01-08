package com.example.paypromodulith.userManager.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

/** DTO for {@link com.example.paypromodulith.entity.Organisation} */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganisationDto implements Serializable {
  private UUID id;
  private Instant createdAt;
  private String name;
  private Set<AdminRowDto> adminRows = new LinkedHashSet<>();
  private Set<DepartmentDto> departments = new LinkedHashSet<>();
  private Set<FileDto> files = new LinkedHashSet<>();
  private SpaceDto space;
}
