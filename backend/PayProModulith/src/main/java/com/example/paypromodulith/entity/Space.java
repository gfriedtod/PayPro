package com.example.paypromodulith.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "space")
@Builder
@NoArgsConstructor
public class Space {
    public Space(UUID id, OffsetDateTime createdAt, Set<Admin> admins, Set<Organisation> organisations) {
        this.id = id;
        this.createdAt = createdAt;
        this.admins = admins;
        this.organisations = organisations;
    }

    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "space")
    private Set<Admin> admins = new LinkedHashSet<>();

    @OneToMany(mappedBy = "space")
    private Set<Organisation> organisations = new LinkedHashSet<>();

}