package com.iuran_bulanan_warga.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iuran_bulanan_warga.Models.Entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    // Check Exist By Email
    @Query("SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM Users u WHERE u.email = :email")
    Boolean existByEmail(@Param("email") String email);

}
