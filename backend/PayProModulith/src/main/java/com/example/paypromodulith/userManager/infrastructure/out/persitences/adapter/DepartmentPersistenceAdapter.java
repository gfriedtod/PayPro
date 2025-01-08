package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.userManager.application.out.DepartmentOutputPort;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.DepartmentMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.DepartmentRepository;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements DepartmentOutputPort {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public List<DepartmentDto> findAllByOrganisation(UUID organisationDto) {
        return departmentRepository.findAllByOrganisationId(organisationDto).stream().map(DepartmentMapper::toDto).toList();
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        return DepartmentMapper.toDto(departmentRepository.save(DepartmentMapper.toEntity(departmentDto)));
    }
}
