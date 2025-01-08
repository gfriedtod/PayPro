package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.AdminUseCase;
import com.example.paypromodulith.userManager.application.out.AdminOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class AdminService implements AdminUseCase {

    private final AdminOutputPort adminOutputPort;
    @Override
    public List<AdminDto> fetchAllAdminByOrganisationId(UUID idOrganisation) {
        return adminOutputPort.fetchAllAdminByOrganisationId(idOrganisation);
    }

    @Override
    public List<AdminDto> fetchAllAdminBySpaceId(UUID idOrganisation) {
        return adminOutputPort.fetchAllAdminBySpaceId(idOrganisation);
    }
}
