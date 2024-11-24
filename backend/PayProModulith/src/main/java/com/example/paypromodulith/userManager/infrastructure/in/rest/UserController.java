package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.model.UserDto;
import com.example.paypromodulith.userManager.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<?> findAllByOrganisation(@PathVariable String organisationId) {

        try {
            return ResponseEntity.ok(userService.findAllByOrganisation(OrganisationDto.builder().id(UUID.fromString(organisationId)).name("").build()));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.create(userDto));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
