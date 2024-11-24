package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.entity.AdminRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRowRepository extends JpaRepository<AdminRow, Long> {

  Optional<List<AdminRow>> findAllByAdmin(Admin admin);
}