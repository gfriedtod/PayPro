package com.example.paypromodulith.userManager.infrastructure.out.persitences.entity;

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
@Table(name = "files")
public class File {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("now()")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "name")
    private String name;

    @Column(name = "date_file")
    private LocalDate dateFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @ColumnDefault("'https://docs.google.com/document/d/1dcwSw2JbVDjKLf9T7NNMSrmQToyqvN7VJva5iQX84RE/edit?tab=t.0'")
    @Column(name = "link", length = Integer.MAX_VALUE)
    private String link;

}