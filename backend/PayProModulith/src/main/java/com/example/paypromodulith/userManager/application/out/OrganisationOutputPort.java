package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;

import java.util.List;
import java.util.UUID;

public interface OrganisationOutputPort {
  List<OrganisationDto> findByAdmin(AdminDto adminDto);
   List<OrganisationDto> findBySpaceId(UUID space);
  OrganisationDto saveOrganisation(OrganisationDto organisationDto);
}
