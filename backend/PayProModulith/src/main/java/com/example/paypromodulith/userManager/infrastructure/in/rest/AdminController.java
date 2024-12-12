package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @GetMapping("/organisation/{organisationId}")
  public ResponseEntity<?> fetchAllAdminByOrganisationId(@PathVariable UUID organisationId) {
    try {
      return ResponseEntity.ok(adminService.fetchAllAdminByOrganisationId(organisationId));
    } catch (Exception e) {
      log.error("e: ", e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/space/{spaceId}")
  public ResponseEntity<?> fetchAllAdminBySpaceId(@PathVariable UUID spaceId) {
    try {
      return ResponseEntity.ok(adminService.fetchAllAdminBySpaceId(spaceId));
    } catch (Exception e) {
      log.error("e: ", e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
