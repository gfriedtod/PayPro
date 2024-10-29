package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.UserUseCase;
import com.example.paypromodulith.userManager.application.out.UserOutputPort;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;
    @Override
    public List<UserDto> findAll() {
        return userOutputPort.findAll();
    }

    @Override
    public UserDto create(UserDto userDto) {
        return userOutputPort.create(userDto);
    }

    @Override
    public void delete(UUID userId) {

    }

    @Override
    public UserDto update(UserDto userDto) {
        return userOutputPort.update(userDto);
    }

    @Override
    public List<UserDto> findAllByOrganisation(OrganisationDto organisation) {
        return userOutputPort.findAllByOrganisation(organisation);
    }
}
