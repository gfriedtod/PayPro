package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.AdminDto;

import java.util.List;
import java.util.UUID;

public interface AdminUseCase {

   List<AdminDto> fetchAllAdminByOrganisationId(UUID idOrganisation);
   List<AdminDto> fetchAllAdminBySpaceId(UUID idOrganisation);


}
