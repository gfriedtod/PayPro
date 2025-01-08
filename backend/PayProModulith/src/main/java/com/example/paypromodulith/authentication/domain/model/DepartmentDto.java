package com.example.paypromodulith.authentication.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

/** DTO for {@link com.example.paypromodulith.entity.Department} */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto implements Serializable {
  private UUID id;
  private Instant createdAt;
  private String name;
  private OrganisationDto organisation;
}
