package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import java.util.List;

public interface DepartementUseCase {

  List<DepartmentDto> findAllByOrganisation(OrganisationDto organisationDto);

  DepartmentDto create(DepartmentDto departmentDto);
}
