package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Department}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto implements Serializable {
    private UUID id;
    private Instant createdAt;
    private String name;
    private OrganisationDto organisation;
}