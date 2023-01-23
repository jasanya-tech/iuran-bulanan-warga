package com.iuran_bulanan_warga.Models.Repositories;

import com.iuran_bulanan_warga.Models.Entities.Houses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<Houses, Integer> {
  
}
