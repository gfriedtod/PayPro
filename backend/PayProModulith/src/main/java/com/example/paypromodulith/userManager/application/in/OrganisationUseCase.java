package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import java.util.List;

public interface OrganisationUseCase {

  List<OrganisationDto> findByAdmin(AdminDto adminDto);

  OrganisationDto saveOrganisation(OrganisationDto organisationDto);
}
