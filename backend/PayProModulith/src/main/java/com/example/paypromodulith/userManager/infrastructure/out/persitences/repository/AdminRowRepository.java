package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.AdminRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRowRepository extends JpaRepository<AdminRow, Long> {
}