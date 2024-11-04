package com.example.paypromodulith.userManager.domain.model;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link File}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto implements Serializable {
    private UUID id;
    private Instant createdAt;
    private String name;
    private LocalDate dateFile;
    private UserDto user;
    private OrganisationDto organisation;
    private String link;

}