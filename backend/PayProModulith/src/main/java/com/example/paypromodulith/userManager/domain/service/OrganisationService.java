package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.OrganisationUseCase;
import com.example.paypromodulith.userManager.application.out.OrganisationOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganisationService implements OrganisationUseCase {
    private final OrganisationOutputPort organisationOutputPort;
    @Override
    public List<OrganisationDto> findByAdmin(AdminDto adminDto) {
        return organisationOutputPort.findByAdmin(adminDto);
    }

    @Override
    public List<OrganisationDto> findBySpaceId(UUID space) {
        return organisationOutputPort.findBySpaceId(space);
    }

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        return organisationOutputPort.saveOrganisation(organisationDto);
    }
}
