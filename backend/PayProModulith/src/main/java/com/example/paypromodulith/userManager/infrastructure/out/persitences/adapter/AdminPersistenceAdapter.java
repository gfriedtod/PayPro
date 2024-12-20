package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.userManager.application.out.AdminOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.AdminMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.AdminRowMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.AdminRepository;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.AdminRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminPersistenceAdapter implements AdminOutputPort {

    private final AdminRowRepository adminRowRepository;
    private final AdminRepository adminRepository;
    @Override
    public List<AdminDto> fetchAllAdminByOrganisationId(UUID idOrganisation) {
        return adminRowRepository.findAllByOrganisationId(idOrganisation).stream().map(row -> AdminMapper.toDto(row.getAdmin())).toList();
    }

    @Override
    public List<AdminDto> fetchAllAdminBySpaceId(UUID idSpace) {
        return adminRepository.findAllBySpaceId(idSpace).stream().map(AdminMapper::toDtoWithoutDependencies).toList();
    }
}
