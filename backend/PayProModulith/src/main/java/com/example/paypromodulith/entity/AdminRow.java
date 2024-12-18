package com.example.paypromodulith.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("gen_random_uuid()")
    @JoinColumn(name = "organisation")
    private Organisation organisation;

}