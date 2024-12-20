package com.example.paypromodulith.authentication.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link com.example.paypromodulith.entity.Admin} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto implements Serializable {
  private UUID id;
  private Instant createdAt;
  private String name;
  private String email;
  private String password;
  private RoleDto role;
  private Set<AdminDepartmentDto> adminDepartments = new LinkedHashSet<>();
  private Set<AdminRowDto> adminRows = new LinkedHashSet<>();
  private SpaceDto space;
}
