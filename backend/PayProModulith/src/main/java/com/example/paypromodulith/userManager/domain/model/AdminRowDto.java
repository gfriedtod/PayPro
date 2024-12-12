package com.example.paypromodulith.userManager.domain.model;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

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
    private List<List<UUID>> departmentList;
}