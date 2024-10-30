package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.userManager.application.out.DepartementOutputPort;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.Organisation;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements DepartementOutputPort {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<DepartmentDto> findAllByOrganisation(OrganisationDto organisationDto) {
        return departmentRepository.findAllByOrganisation(modelMapper.map(organisationDto, Organisation.class)).stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList();
    }
}
