package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.OrganisationUseCase;
import com.example.paypromodulith.userManager.application.out.OrganisationOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganisationService implements OrganisationUseCase {
    private final OrganisationOutputPort organisationOutputPort;
    @Override
    public List<OrganisationDto> findByAdmin(AdminDto adminDto) {
        return organisationOutputPort.findByAdmin(adminDto);
    }

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        return organisationOutputPort.saveOrganisation(organisationDto);
    }
}
