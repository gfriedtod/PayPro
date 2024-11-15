package com.example.paypromodulith.authentication.infracsturcture.out.adapter;

import com.example.paypromodulith.authentication.application.output.UserOutputPort;
import com.example.paypromodulith.authentication.domain.model.UserDto;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
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
        return modelMapper.map(userRepository.findByEmail(username), UserDto.class);
    }
}
