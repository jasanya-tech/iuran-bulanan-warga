package com.iuran_bulanan_warga.Models.Repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.iuran_bulanan_warga.Models.Entities.ImageHouses;

public interface ImageHouseRepository extends JpaRepository<ImageHouses, Integer> {

    Optional<ImageHouses> findByFileName(String fileName);
}
