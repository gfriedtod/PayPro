package com.example.paypromodulith.userManager.domain.model;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.example.paypromodulith.entity.AdminRow}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRowDto implements Serializable {
    Long id;
    OffsetDateTime createdAt;
    AdminDto admin;
    OrganisationDto organisation;
}