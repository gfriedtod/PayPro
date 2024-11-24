package com.example.paypromodulith.userManager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.entity.Admin}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto implements Serializable {
    private UUID id;
    private OffsetDateTime createdAt;
    private String name;
    private String email;
    private String password;
    private Set<AdminRowDto> adminRows = new LinkedHashSet<>();
}