package com.example.paypromodulith.userManager.domain.model;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.Organisation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganisationDto implements Serializable {
    UUID id;
    Instant createdAt;
    String name;
}