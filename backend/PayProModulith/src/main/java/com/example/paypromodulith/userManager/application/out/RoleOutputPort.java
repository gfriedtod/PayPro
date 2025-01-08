package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.RoleDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoleOutputPort {
    List<RoleDto> fetchAll();

}
