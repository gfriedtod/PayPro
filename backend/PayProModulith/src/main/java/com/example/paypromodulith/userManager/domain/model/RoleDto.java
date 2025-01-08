package com.example.paypromodulith.userManager.domain.model;

import com.example.paypromodulith.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/** DTO for {@link Role} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto implements Serializable {
  private UUID id;
  private OffsetDateTime createdAt;
  private String name;
}
