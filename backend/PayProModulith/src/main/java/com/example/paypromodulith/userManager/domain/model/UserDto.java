package com.example.paypromodulith.userManager.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

/**
 * DTO for {@link com.example.paypromodulith.entity.User}
 */
@Getter
@Setter
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