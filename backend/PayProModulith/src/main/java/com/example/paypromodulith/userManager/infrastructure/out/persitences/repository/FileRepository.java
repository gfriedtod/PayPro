package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.File;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    Collection<Object> findAllByUser(User map);
}