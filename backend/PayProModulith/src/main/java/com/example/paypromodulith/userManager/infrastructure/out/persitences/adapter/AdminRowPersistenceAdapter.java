package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.AdminRow;
import com.example.paypromodulith.userManager.application.out.AdminRowOutputPort;
import com.example.paypromodulith.userManager.domain.model.AdminRowDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.AdminRowMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.AdminRowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminRowPersistenceAdapter implements AdminRowOutputPort {

  private final AdminRowRepository adminRowRepository;

  @Override
  public AdminRowDto create(AdminRowDto adminDto) {

    AdminRow adminRow = adminRowRepository.save(AdminRowMapper.toEntity(adminDto));

    System.out.println(adminRow.getOrganisation().getName());
    return adminRowRepository.findById(adminRow.getId()).map(AdminRowMapper::toDtoWithoutAdmin).orElse(null);
  }
}
