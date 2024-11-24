package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;

import java.util.List;

public interface OrganisationOutputPort {
    List<OrganisationDto> findByAdmin(AdminDto adminDto);
    OrganisationDto saveOrganisation(OrganisationDto organisationDto);

}
