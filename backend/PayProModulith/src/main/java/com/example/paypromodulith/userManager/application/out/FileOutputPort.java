package com.example.paypromodulith.userManager.application.out;

import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;

import java.util.List;

public interface FileOutputPort {
  FileDto save(FileDto fileDto);

  List<FileDto> fetchFilesByUser(UserDto userDto);
}
