package com.example.paypromodulith.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"adminRow\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor  // Ajoutez cette annotation
public class AdminRow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminRow_id_gen")
    @SequenceGenerator(name = "adminRow_id_gen", sequenceName = "adminRow_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("gen_random_uuid()")
    @JoinColumn(name = "admin")
    private Admin admin;

    @ManyToOne(fetch = FetchType.EAGER)
    @ColumnDefault("gen_random_uuid()")
    @JoinColumn(name = "organisation")
    private Organisation organisation;

    @Column(name = "\"departmentList\"",columnDefinition = "uuid[]")
    private List<UUID> departmentList;

}