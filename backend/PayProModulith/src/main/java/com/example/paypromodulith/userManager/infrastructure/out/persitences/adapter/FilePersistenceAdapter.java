package com.example.paypromodulith.userManager.infrastructure.out.persitences.adapter;

import com.example.paypromodulith.entity.File;
import com.example.paypromodulith.userManager.application.out.FileOutputPort;
import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.mapper.FileMapper;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.repository.FileRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class FilePersistenceAdapter implements FileOutputPort {
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public FileDto save(FileDto fileDto) {
        return FileMapper.toDto(fileRepository.save(FileMapper.toEntity(fileDto)));
    }

    @Transactional
    @Override
    public List<FileDto> fetchFilesByUser(UserDto userDto) {
        return fileRepository.findAllByUserId(
                userDto.getId()
                ).stream().map(file -> FileMapper.toDto((File) file))
                .toList();
    }
}
