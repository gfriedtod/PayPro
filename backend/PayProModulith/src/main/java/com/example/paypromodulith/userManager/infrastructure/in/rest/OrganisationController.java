package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.AdminDto;
import com.example.paypromodulith.userManager.domain.model.OrganisationDto;
import com.example.paypromodulith.userManager.domain.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("organisation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationService organisationService;

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<?> findByAdmin(@PathVariable String adminId) {
        try {
            System.out.println("idok"+ adminId);
            return ResponseEntity.ok(organisationService.findByAdmin(AdminDto.builder().id(UUID.fromString(adminId)).name("").build()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveOrganisation(@RequestBody OrganisationDto organisationDto) {
        try {
            return ResponseEntity.ok(organisationService.saveOrganisation(organisationDto));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
