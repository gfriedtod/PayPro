package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.RoleDto;

import java.util.List;

public interface RoleUseCase {

    List<RoleDto>fetchAll();
}
