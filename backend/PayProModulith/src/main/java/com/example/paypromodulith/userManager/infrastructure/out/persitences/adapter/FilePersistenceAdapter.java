package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.File;
import com.example.paypromodulith.entity.User;
import com.example.paypromodulith.userManager.application.in.FileUseCase;
import com.example.paypromodulith.userManager.application.out.FileOutputPort;
import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@AllArgsConstructor
public class FilePersistenceAdapter implements FileOutputPort {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    @Override
    public FileDto save(FileDto fileDto) {
        return modelMapper.map(fileRepository.save(modelMapper.map(fileDto, File.class)),FileDto.class);
    }

    @Override
    public List<FileDto> fetchFilesByUser(UserDto userDto) {
        return fileRepository.findAllByUser(modelMapper.map(userDto, User.class)).stream().map(file -> modelMapper.map(file, FileDto.class)).toList();
    }
}
