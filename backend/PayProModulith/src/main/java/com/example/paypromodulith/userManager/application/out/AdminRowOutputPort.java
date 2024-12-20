package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.AdminRowDto;
import org.springframework.stereotype.Component;

@Component
public interface AdminRowOutputPort {
    AdminRowDto create(AdminRowDto adminDto);

}
