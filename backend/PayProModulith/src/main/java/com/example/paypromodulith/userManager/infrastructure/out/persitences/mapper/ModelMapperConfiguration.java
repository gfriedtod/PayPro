package com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper;

import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {

//        TypeMap<Organisation, OrganisationDto> type =  modelMapper.createTypeMap(Organisation.class, OrganisationDto.class);
//        type.addMapping(Organisation::getId, OrganisationDto::setId);
//          type.addMapping(Organisation::getName, OrganisationDto::setName);
//        type.addMapping(map -> map.getAdminRows().stream().map(admin -> modelMapper.map(admin, AdminDto.class)).toList(), OrganisationDto::setAdminRows);
//        type.addMapping(map -> map.getDepartments().stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList(), OrganisationDto::setDepartments);
//        type.addMapping(map-> map.getFiles().stream().map(file -> modelMapper.map(file, FileDto.class)).toList(), OrganisationDto::setFiles);

        return new ModelMapper();
    }
}
