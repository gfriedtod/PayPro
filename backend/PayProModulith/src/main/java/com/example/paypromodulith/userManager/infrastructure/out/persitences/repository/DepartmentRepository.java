package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.entity.Department;
import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findAllByOrganisation(Organisation organisation);

}