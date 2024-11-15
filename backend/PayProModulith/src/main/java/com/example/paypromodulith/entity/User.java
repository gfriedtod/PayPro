package com.example.paypromodulith.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @ColumnDefault("gen_random_uuid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "rule")
    private String rule;

    @Column(name = "cni", length = Integer.MAX_VALUE)
    private String cni;

    @ColumnDefault("'MALE'")
    @Column(name = "gender", length = Integer.MAX_VALUE)
    private String gender;

    @ColumnDefault("now()")
    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @ColumnDefault("'Douala'")
    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @ColumnDefault("'https://afriksportsmagazine.com/wp-content/uploads/2024/02/C3N09690.webp'")
    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;

    @ColumnDefault("'+237 677777777'")
    @Column(name = "phone", length = Integer.MAX_VALUE)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

}