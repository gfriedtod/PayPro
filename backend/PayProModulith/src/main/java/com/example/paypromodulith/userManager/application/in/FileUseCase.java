package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.File;

import java.util.List;

public interface FileUseCase {
    FileDto save(FileDto fileDto);

    List<FileDto> fetchFilesByUser(UserDto userDto);




}
