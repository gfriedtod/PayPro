package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import java.util.List;
import java.util.UUID;

public interface DepartementUseCase {

  List<DepartmentDto> findAllByOrganisation(UUID organisationDto);

  DepartmentDto create(DepartmentDto departmentDto);
}
