package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.entity.User;
import com.example.paypromodulith.userManager.application.out.UserOutputPort;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.OrganisationMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.UserMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserOutputPort {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(userDto)));
    }

    @Override
    public void delete(UUID userId) {

    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Transactional
    @Override
    public List<UserDto> findAllByOrganisation(OrganisationDto organisation) {
        var list = userRepository.findAllByOrganisationId(organisation.getId()).stream().map(UserMapper::toDto).toList();
        System.out.println(list.size());
        return list;
    }
}
