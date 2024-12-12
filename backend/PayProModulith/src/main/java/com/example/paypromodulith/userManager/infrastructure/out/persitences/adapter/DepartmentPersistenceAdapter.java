package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.application.out.DepartmentOutputPort;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.DepartmentMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements DepartmentOutputPort {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public List<DepartmentDto> findAllByOrganisation(OrganisationDto organisationDto) {
        return departmentRepository.findAllByOrganisation(modelMapper.map(organisationDto, Organisation.class)).stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList();
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        return DepartmentMapper.toDto(departmentRepository.save(DepartmentMapper.toEntity(departmentDto)));
    }
}
