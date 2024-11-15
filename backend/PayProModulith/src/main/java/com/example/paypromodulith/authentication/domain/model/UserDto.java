package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String gender;
    private LocalDate dateBirth;
    private String address;
    private String image;
    private String phone;
}