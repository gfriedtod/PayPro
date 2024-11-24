package com.example.paypromodulith.authentication.infracsturcture.out.persitence.repository;

import com.example.paypromodulith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String username);
}