package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentOutputPort {
  List<DepartmentDto> findAllByOrganisation(UUID organisationDto);

  DepartmentDto save(DepartmentDto departmentDto);
}
