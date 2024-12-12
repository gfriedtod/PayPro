package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.userManager.domain.model.AdminDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.example.paypromodulith.entity.AdminRow}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRowDto implements Serializable {
    private Long id;
    private OffsetDateTime createdAt;
    private AdminDto admin;
    private OrganisationDto organisation;
    private List<List<UUID>> departmentList;
}