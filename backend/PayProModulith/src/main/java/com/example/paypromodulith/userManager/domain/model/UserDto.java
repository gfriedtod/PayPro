package com.example.paypromodulith.userManager.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
    private UUID id;
    private Instant createdAt;
    private DepartmentDto department;
    private OrganisationDto organisation;
    private String displayName;
    private String name;
    private String email;
    private String password;
    private String rule;
    private String cni;
    private String phone;
    private String address;
    private LocalDate dateBirth;
    private String gender;
    private String image;
}