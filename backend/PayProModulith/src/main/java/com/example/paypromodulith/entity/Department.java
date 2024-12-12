package com.example.paypromodulith.entity;

import com.example.paypromodulith.AdminDepartment;
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
@Table(name = "departments")
@Builder
@NoArgsConstructor
@AllArgsConstructor  // Ajoutez cette annotation
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)

    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @OneToMany(mappedBy = "department")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<AdminDepartment> adminDepartments = new LinkedHashSet<>();

}