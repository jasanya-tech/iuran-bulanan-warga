package com.iuran_bulanan_warga.Models.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iuran_bulanan_warga.Helpers.DTO.Repository.SearchByNameOutput;
import com.iuran_bulanan_warga.Models.Entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    // Check Exist By Email
    @Query("SELECT CASE WHEN count(u) > 0 THEN true ELSE false END FROM Users u WHERE u.email = :email")
    Boolean existByEmail(@Param("email") String email);

    // Search users by full name
    @Query(value = "SELECT new com.iuran_bulanan_warga.Helpers.DTO.Repository.SearchByNameOutput(u.id, u.fullName)  FROM Users u WHERE u.fullName LIKE %:keyword%")
    List<SearchByNameOutput> searchByFullName(@Param("keyword") String keyword);
}
