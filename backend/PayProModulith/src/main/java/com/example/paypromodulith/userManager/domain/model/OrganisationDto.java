package com.example.paypromodulith.userManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.Organisation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDto implements Serializable {
    UUID id;
    Instant createdAt;
    String name;
}