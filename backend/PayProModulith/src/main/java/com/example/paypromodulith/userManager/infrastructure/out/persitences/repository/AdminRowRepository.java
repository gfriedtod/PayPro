package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.entity.AdminRow;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AdminRowRepository extends JpaRepository<AdminRow, Long> {

  Optional<List<AdminRow>> findAllByAdmin(Admin admin);

  List<AdminRow> findAllByOrganisationId(UUID idOrganisation);
}