package com.example.paypromodulith.entity;

import com.example.paypromodulith.AdminDepartment;
import com.example.paypromodulith.Role;
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
@Table(name = "admin")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "password", length = Integer.MAX_VALUE)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("'f8398da3-a3a8-4541-b993-e2fc004ddc72'")
    @JoinColumn(name = "role")
    private Role role;

    @OneToMany(mappedBy = "admin")
    private Set<AdminDepartment> adminDepartments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<AdminRow> adminRows = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @ColumnDefault("'a977ae25-5ea3-4e3d-9702-55aff68dca1f'")
    @JoinColumn(name = "space")
    private Space space;

}