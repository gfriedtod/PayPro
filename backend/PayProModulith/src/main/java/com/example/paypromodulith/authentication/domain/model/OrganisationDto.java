package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.Organisation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link Organisation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDto implements Serializable {
    private UUID id;
    private Instant createdAt;
    private String name;
}