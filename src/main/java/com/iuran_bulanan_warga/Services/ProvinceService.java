package com.iuran_bulanan_warga.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.ProvinceRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Provinces;
import com.iuran_bulanan_warga.Models.Repositories.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService {
  @Autowired
  ProvinceRepository provinceRepository;

  public ResponseEntity<?> serviceGetAll() {
    try {
      List<Provinces> provinces = provinceRepository.findAll();
      if (provinces.isEmpty()) {
        throw new NoSuchElementException("No province found!");
      }
      return ResponseEntity.ok().body(provinces);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<Provinces> province = provinceRepository.findById(id);
      if (province.isPresent()) {
        return ResponseEntity.ok().body(province.get());
      } else {
        throw new NoSuchElementException("No province found!");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreate(ProvinceRequest provinceRequest) {
    try {
      Provinces province = new Provinces(
          provinceRequest.getProvinceName()
        );
      provinceRepository.save(province);
      return ResponseEntity.ok().body(province);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, ProvinceRequest provinceRequest) {
    try {
      Optional<Provinces> province = provinceRepository.findById(id);
      if (!province.isPresent()) {
        throw new NoSuchElementException("province" + id + "doesn't exist!");
      }
      Provinces provinceData = province.get();
      provinceData.setProvinceName(provinceRequest.getProvinceName());
      provinceRepository.save(provinceData);
      return ResponseEntity.ok().body(province);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteById(Integer id) {
    try {
      Optional<Provinces> province = provinceRepository.findById(id);
      if (!province.isPresent()) {
        throw new NoSuchElementException("province " + id + " doesn't exist!");
      }
      provinceRepository.deleteById(id);
      return ResponseEntity.ok().body(new MessageResponse("province " + id + " has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      provinceRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All provinces has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
