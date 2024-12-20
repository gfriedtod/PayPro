package com.example.paypromodulith.userManager.infrastructure.out.persitences.repository;


import com.example.paypromodulith.entity.Organisation;
import com.example.paypromodulith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByOrganisationId(UUID organisation);

    @Query(value = "SELECT  u.*" +
            "FROM users u" +
            "        JOIN public.organisations o on u.organisation_id = o.id" +
            "        JOIN public.admin_row ar on o.id = ar.organisation" +
            "        JOIN public.admin_department ad on u.department_id = ad.department" +
            "        where ar.admin = :idAdmin and organisation_id = :idOrganisation" ,nativeQuery = true)
    List<User> findAllByOrganisationIdAndAdminId(
            @Param("idOrganisation") UUID idOrganisation,
            @Param("idAdmin") UUID idAdmin
    );
}