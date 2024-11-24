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

    @OneToMany(mappedBy = "organisation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<AdminRow> adminRows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<Department> departments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<File> files = new LinkedHashSet<>();

    @OneToMany(mappedBy = "organisation",fetch = FetchType.LAZY)
    private Set<User> users;

    public Organisation(UUID id, Instant createdAt, String name, Set<AdminRow> adminRows, Set<Department> departments, Set<File> files, Set<User> users) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.adminRows = adminRows;
        this.departments = departments;
        this.files = files;
        this.users = users;
    }

    public Organisation() {

    }
}