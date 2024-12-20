package com.example.paypromodulith.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "organisations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organisation {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organisation",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<AdminRow> adminRows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<File> files = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("'a977ae25-5ea3-4e3d-9702-55aff68dca1f'")
    @JoinColumn(name = "space")
    private Space space;

}