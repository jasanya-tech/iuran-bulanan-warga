package com.iuran_bulanan_warga.Models.Repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iuran_bulanan_warga.Models.Entities.ImageHouses;

public interface ImageHouseRepository extends JpaRepository<ImageHouses, Integer> {

    @Query(value = "SELECT ih FROM ImageHouses ih WHERE ih.house.id = :houseId")
    Set<ImageHouses> getPictures(@Param("houseId") Integer houseId);
}
