package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;

import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.Organisation;
import com.example.paypromodulith.userManager.infrastructure.out.persitences.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByOrganisation(Organisation organisation);
}