package com.iuran_bulanan_warga.Models.Repositories;

import com.iuran_bulanan_warga.Models.Entities.DuesType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DuesTypeRepository extends JpaRepository<DuesType, Integer> {
  
}
