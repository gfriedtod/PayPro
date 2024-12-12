package com.example.paypromodulith;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.entity.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "admin_department")
public class AdminDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_department_id_gen")
    @SequenceGenerator(name = "admin_department_id_gen", sequenceName = "admin_department_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("gen_random_uuid()")
    @JoinColumn(name = "admin")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @ColumnDefault("gen_random_uuid()")
    @JoinColumn(name = "department")
    private Department department;

}