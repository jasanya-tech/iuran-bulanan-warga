package com.iuran_bulanan_warga.Models.Repositories;

import com.iuran_bulanan_warga.Models.Entities.Cities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Cities, Integer> {
    // Find all city by provinc id
    Iterable<Cities> findAllByProvinceId(Integer provinceId);

}
