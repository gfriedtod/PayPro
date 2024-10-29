package com.example.paypromodulith.userManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.AdminRow}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRowDto implements Serializable {
    Long id;
    OffsetDateTime createdAt;
    AdminDto admin;
    OrganisationDto organisation;
}