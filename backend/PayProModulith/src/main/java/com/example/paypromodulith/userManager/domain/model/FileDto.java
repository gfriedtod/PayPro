package com.example.paypromodulith.userManager.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

/** DTO for {@link com.example.paypromodulith.entity.File} */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto implements Serializable {
  private UUID id;
  private Instant createdAt;
  private String name;
  private LocalDate dateFile;
  private UserDto user;
  private OrganisationDto organisation;
  private String link;
}
