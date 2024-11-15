package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for {@link Admin}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto implements Serializable {
    private UUID id;
    private Instant createdAt;
    private String name;
    private String email;
    private String password;
}