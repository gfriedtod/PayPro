package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserOutputPort {

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    void delete(UUID userId);

    UserDto update(UserDto userDto);
    List<UserDto> findAllByOrganisation(OrganisationDto organisation,UUID idAmin);

}
