package com.iuran_bulanan_warga.Models.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iuran_bulanan_warga.Helpers.Entities.ERole;
import com.iuran_bulanan_warga.Models.Entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Role r WHERE r.roleName = :role")
    Boolean existByRoleName(@Param("role") ERole role);

    Optional<Role> findByRoleName(ERole role);
}
