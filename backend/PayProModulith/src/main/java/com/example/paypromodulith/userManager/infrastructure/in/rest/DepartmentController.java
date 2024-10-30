package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/department")
@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<?> findAllByOrganisation(@PathVariable String organisationId) {
        try {
            return ResponseEntity.ok(departmentService.findAllByOrganisation(OrganisationDto.builder().id(UUID.fromString(organisationId)).name("").build()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
