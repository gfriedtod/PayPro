package com.example.paypromodulith.userManager.infrastructure.configuration;

import com.example.paypromodulith.userManager.domain.service.UserService;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter.UserPersistenceAdapter;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, ModelMapper modelMapper) {
        return new UserPersistenceAdapter(modelMapper, userRepository);
    }
    @Bean
    UserService userService(UserPersistenceAdapter userPersistenceAdapter) {
        return new UserService(userPersistenceAdapter);
    }
}
