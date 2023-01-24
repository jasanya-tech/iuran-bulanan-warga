package com.iuran_bulanan_warga.Models.Repositories;

import com.iuran_bulanan_warga.Models.Entities.Provinces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Provinces, Integer> {
  
}
