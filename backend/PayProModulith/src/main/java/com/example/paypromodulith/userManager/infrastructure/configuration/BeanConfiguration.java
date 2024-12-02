package com.example.paypromodulith.userManager.infrastructure.configuration;

import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.service.DepartmentService;
import com.example.paypromodulith.userManager.domain.service.FileService;
import com.example.paypromodulith.userManager.domain.service.UserService;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter.DepartmentPersistenceAdapter;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter.FilePersistenceAdapter;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter.OrganisationPersistenceAdapter;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter.UserPersistenceAdapter;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    UserPersistenceAdapter userPersistenceAdapter(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        return new UserPersistenceAdapter(modelMapper, userRepository,passwordEncoder);
    }

    @Bean
    DepartmentPersistenceAdapter departmentPersistenceAdapter(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        return new DepartmentPersistenceAdapter(departmentRepository, modelMapper);
    }
    @Bean
    FilePersistenceAdapter filePersistenceAdapter(FileRepository fileRepository, ModelMapper modelMapper) {
        return new FilePersistenceAdapter(fileRepository, modelMapper);
    }
    @Bean
    OrganisationPersistenceAdapter organisationPersistenceAdapter(ModelMapper modelMapper, OrganisationRepository organisationRepository, AdminRowRepository adminRowRepository) {
        return new OrganisationPersistenceAdapter(modelMapper, organisationRepository, adminRowRepository);
    }
    @Bean
    UserService userService(UserPersistenceAdapter userPersistenceAdapter) {
        return new UserService(userPersistenceAdapter);
    }

    @Bean
    DepartmentService departmentService(DepartmentPersistenceAdapter departmentPersistenceAdapter) {
        return new DepartmentService(departmentPersistenceAdapter);
    }

    @Bean
    FileService fileService(FilePersistenceAdapter filePersistenceAdapter) {
        return new FileService(filePersistenceAdapter);
    }


}
