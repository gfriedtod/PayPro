package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.DepartmentDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.service.DepartmentService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/department")
@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/organisation/{organisationId}")
    public ResponseEntity<?> findAllByOrganisation(@PathVariable String organisationId) {
        try {
            return ResponseEntity.ok(departmentService.findAllByOrganisation(UUID.fromString(organisationId)));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DepartmentDto departmentDto) {
        try {
            return ResponseEntity.ok(departmentService.create(departmentDto));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
