package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.entity.Admin;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    @Cacheable("idSpace")
    List<Admin> findAllBySpaceId(UUID idSpace);
}