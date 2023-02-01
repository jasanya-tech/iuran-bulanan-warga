package com.iuran_bulanan_warga.Models.Repositories;

import java.util.List;

import com.iuran_bulanan_warga.Models.Entities.Houses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseRepository extends JpaRepository<Houses, Integer> {

  @Query(value = "SELECT h FROM Houses h WHERE h.owner.id = :userId")
  List<Houses> findHousesByUserId(@Param("userId") Integer userId);

  @Query(value = "SELECT " +
      "PERIOD_DIFF(DATE_FORMAT(CURRENT_DATE, '%Y%m'),DATE_FORMAT(h.created_at, '%Y%m')) as DiffMonth " +
      "FROM Houses h " +
      "WHERE PERIOD_DIFF(DATE_FORMAT(CURRENT_DATE, '%Y%m'),DATE_FORMAT(h.created_at, '%Y%m')) >= 1 " +
      "AND h.id = :houseId")
  Integer findDiffMonth(@Param("houseId") Integer houseId);

}
