package com.example.paypromodulith.authentication.infracsturcture.out.adapter;

import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import com.example.paypromodulith.authentication.domain.model.*;
import com.example.paypromodulith.authentication.infracsturcture.out.mapper.UserMapper;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.UserAuthRepository;
import org.modelmapper.ModelMapper;


public class UserAuthPersistenceAdapter implements UserOutputPort {

    private final UserAuthRepository userRepository;
    private final ModelMapper modelMapper;

    public UserAuthPersistenceAdapter(UserAuthRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        var user = userRepository.findByEmail(username);
        return user.map(UserMapper::toDto).orElse(null);
    }

    @Override
    public UserDto login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail());
        return user.map(user1 -> modelMapper.map(user1, UserDto.class)).orElse(null);

    }
}
