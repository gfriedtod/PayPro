package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.DepartementUseCase;
import com.example.paypromodulith.userManager.application.out.DepartementOutputPort;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentService implements DepartementUseCase {

    private final DepartementOutputPort departementOutputPort;
    @Override
    public List<DepartmentDto> findAllByOrganisation(OrganisationDto organisationDto) {
        return departementOutputPort.findAllByOrganisation(organisationDto);
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        return departementOutputPort.save(departmentDto);
    }
}
