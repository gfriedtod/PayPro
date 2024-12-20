package com.example.paypromodulith.userManager.infrastructure.in.rest;

import com.example.paypromodulith.userManager.domain.model.AdminRowDto;
import com.example.paypromodulith.userManager.domain.service.AdminRowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adminRow")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class AdminRowController {
    private final AdminRowService adminRowService;

    @PostMapping("")
    ResponseEntity<?> create (@RequestBody AdminRowDto adminRowDto) {
        try {
            return ResponseEntity.ok(adminRowService.create(adminRowDto));
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
