package com.example.paypromodulith.authentication.infracsturcture.out.configuration;

import com.example.paypromodulith.authentication.domain.service.AdminAuthService;
import com.example.paypromodulith.authentication.domain.service.ClientService;
import com.example.paypromodulith.authentication.infracsturcture.out.adapter.AdminAuthPersitenceAdapter;
import com.example.paypromodulith.authentication.infracsturcture.out.adapter.UserAuthPersistenceAdapter;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.AdminAuthRepository;
import com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository.UserAuthRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    AdminAuthPersitenceAdapter adminAuthPersitenceAdapter(AdminAuthRepository adminAuthRepository, ModelMapper modelMapper){
        return new AdminAuthPersitenceAdapter(adminAuthRepository, modelMapper);
    }
    @Bean
    UserAuthPersistenceAdapter userAuthPersistenceAdapter(UserAuthRepository userRepository, ModelMapper modelMapper){
        return new UserAuthPersistenceAdapter(userRepository,modelMapper);
    }
    @Bean
    AdminAuthService adminAuthService(AdminAuthPersitenceAdapter adminAuthPersitenceAdapter, PasswordEncoder passwordEncoder){
        return new AdminAuthService(adminAuthPersitenceAdapter,passwordEncoder);
    }

    @Bean
    ClientService clientService(UserAuthPersistenceAdapter userAuthPersistenceAdapter,AdminAuthPersitenceAdapter adminAuthPersitenceAdapter){
        return new ClientService(adminAuthPersitenceAdapter, userAuthPersistenceAdapter);
    }

}
