package com.example.paypromodulith.userManager.application.in;

import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;

import java.util.List;

public interface FileUseCase {
    FileDto save(FileDto fileDto);

    List<FileDto> fetchFilesByUser(UserDto userDto);




}
