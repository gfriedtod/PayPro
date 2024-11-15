package com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository;

import com.example.paypromodulith.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminAuthRepository extends JpaRepository<Admin, UUID> {
    Admin findByEmail(String email);

}