package com.example.paypromodulith.userManager.domain.service;

import com.example.paypromodulith.userManager.application.in.FileUseCase;
import com.example.paypromodulith.userManager.application.out.FileOutputPort;
import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FileService implements FileUseCase {

    private final FileOutputPort fileOutputPort;

    @Override
    public FileDto save(FileDto fileDto) {
        return fileOutputPort.save(fileDto);
    }

    @Override
    public List<FileDto> fetchFilesByUser(UserDto userDto) {
        return fileOutputPort.fetchFilesByUser(userDto);
    }

}
