package com.example.paypromodulith.userManager.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    UUID id;
    Instant createdAt;
    DepartmentDto department;
    OrganisationDto organisation;
    String displayName;
    String name;
    String email;
    String password;
    String rule;
    String cni;
}