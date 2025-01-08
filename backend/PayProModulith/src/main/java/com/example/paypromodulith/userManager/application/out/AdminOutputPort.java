package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.AdminDto;

import java.util.List;
import java.util.UUID;

public interface AdminOutputPort {
  List<AdminDto> fetchAllAdminByOrganisationId(UUID idOrganisation);

  List<AdminDto> fetchAllAdminBySpaceId(UUID idOrganisation);
}
