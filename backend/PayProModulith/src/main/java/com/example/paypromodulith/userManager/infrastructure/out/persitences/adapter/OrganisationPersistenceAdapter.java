package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.Admin;
import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.application.out.OrganisationOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.AdminMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.AdminRowMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.OrganisationMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.AdminRowRepository;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class OrganisationPersistenceAdapter implements OrganisationOutputPort {

    private final ModelMapper modelMapper;
    private final OrganisationRepository organisationRepository;
    private final AdminRowRepository adminRowRepository;


    @Override
    public List<OrganisationDto> findByAdmin(AdminDto adminDto) {
        var adminRows = adminRowRepository.findAllByAdmin(AdminMapper.toEntity(adminDto));
        System.out.println("lenght od admin rows "+adminRows.get().get(0).getOrganisation().getName());
        return adminRows.map(rows -> rows.stream().map(adminRow -> OrganisationMapper.toDto(adminRow.getOrganisation())).toList()).orElse(null);
    }

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        var organisation = OrganisationMapper.toEntity(organisationDto);
        var savedOrganisation = organisationRepository.save(organisation);

        return OrganisationMapper.toDtoWithoutDependencies(savedOrganisation);
    }
}
