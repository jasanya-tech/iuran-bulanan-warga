package com.iuran_bulanan_warga.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iuran_bulanan_warga.Models.Entities.Provinces;
import com.iuran_bulanan_warga.Models.Repositories.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProvinceFeaturesService {
  @Autowired
  ProvinceRepository provinceRepository;

  public ResponseEntity<?> showProvincesWithPage(int page, int size) {
    try {
      List<Provinces> provinces = new ArrayList<Provinces>();

      Pageable paging = PageRequest.of(page, size);
      Page<Provinces> pageProvinces;
      pageProvinces = provinceRepository.findAll(paging);
      provinces = pageProvinces.getContent();

      Map<String, Object> res = new HashMap<>();
      res.put("data", provinces);
      res.put("size", size);
      res.put("currentPage", paging.getPageNumber());
      res.put("totalItems", pageProvinces.getTotalElements());
      res.put("totalPage", pageProvinces.getTotalPages());
      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
