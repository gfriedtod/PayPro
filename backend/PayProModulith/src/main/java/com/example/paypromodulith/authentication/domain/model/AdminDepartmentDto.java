package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.AdminDepartment;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
