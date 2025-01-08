package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.RoleUseCase;
import com.example.paypromodulith.userManager.application.out.RoleOutputPort;
import com.example.paypromodulith.userManager.domain.model.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleUseCase {

    private final RoleOutputPort roleOutputPort;
/**
*
 * @return a list of roles
*/
    @Override
    public List<RoleDto> fetchAll() {
        return roleOutputPort.fetchAll();
    }
}
