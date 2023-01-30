package com.iuran_bulanan_warga.Models.Repositories;

import java.util.List;

import com.iuran_bulanan_warga.Models.Entities.Houses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseRepository extends JpaRepository<Houses, Integer> {
  @Query(value = "SELECT Houses FROM Houses WHERE Houses.owner.id = :userId")
  List<Houses> findHousesByUserId(@Param("userId") Integer userId);
}
