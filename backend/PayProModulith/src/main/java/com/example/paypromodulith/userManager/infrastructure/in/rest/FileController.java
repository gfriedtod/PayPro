package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.FileDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import com.example.paypromodulith.userManager.domain.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("file")
@CrossOrigin("*")
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> fetchFilesByUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(fileService.fetchFilesByUser(UserDto.builder().id(UUID.fromString(userId)).name("").build()));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody FileDto fileDto) {
        try {
            return ResponseEntity.ok(fileService.save(fileDto));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
