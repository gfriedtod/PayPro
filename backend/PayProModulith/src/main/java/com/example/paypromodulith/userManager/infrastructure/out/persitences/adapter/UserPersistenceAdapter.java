package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.userManager.application.out.UserOutputPort;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.Organisation;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.User;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @Override
    public void delete(UUID userId) {

    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> findAllByOrganisation(OrganisationDto organisation) {
        System.out.println("test exist "+modelMapper.map(organisation, Organisation.class).getId());
        var list = userRepository.findAllByOrganisation(modelMapper.map(organisation, Organisation.class)).stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        System.out.println(list.size());
        return list;
    }
}
