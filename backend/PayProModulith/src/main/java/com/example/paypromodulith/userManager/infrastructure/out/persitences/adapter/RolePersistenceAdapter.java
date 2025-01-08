package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.userManager.application.out.RoleOutputPort;
import com.example.paypromodulith.userManager.domain.model.RoleDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.RoleMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleOutputPort {
    private final RoleRepository roleRepository;

/**
*
 * @return a list of roles
*/
    @Override
    public List<RoleDto> fetchAll() {
        return roleRepository.findAll().stream().map(RoleMapper::toDto).toList();
    }
}
