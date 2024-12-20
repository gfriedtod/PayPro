package com.example.paypromodulith.userManager.domain.model;

import com.example.paypromodulith.entity.AdminDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

/** DTO for {@link AdminDepartment} */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDepartmentDto implements Serializable {
  private Long id;
  private OffsetDateTime createdAt;
  private AdminDto admin;
  private DepartmentDto department;
}
