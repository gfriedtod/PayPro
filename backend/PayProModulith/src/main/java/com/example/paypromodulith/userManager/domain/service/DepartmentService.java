package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.DepartementUseCase;
import com.example.paypromodulith.userManager.application.out.DepartmentOutputPort;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DepartmentService implements DepartementUseCase {

  private final DepartmentOutputPort departmentOutputPort;

    @Override
    public List<DepartmentDto> findAllByOrganisation(UUID organisationDto) {
    return departmentOutputPort.findAllByOrganisation(organisationDto);
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
    return departmentOutputPort.save(departmentDto);
    }
}
