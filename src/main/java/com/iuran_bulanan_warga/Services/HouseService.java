package com.iuran_bulanan_warga.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.HouseRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Repositories.CityRepository;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class HouseService {
  @Autowired
  HouseRepository houseRepository;

  @Autowired
  HouseRequest houseRequest;

  @Autowired
  CityRepository cityRepository;

  public ResponseEntity<?> serviceGetAll() {
    try {
      List<Houses> houses = houseRepository.findAll();
      if (houses.isEmpty()) {
        throw new NoSuchElementException("No house found!");
      }
      return ResponseEntity.ok().body(houses);
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<Houses> house = houseRepository.findById(id);
      if (house.isPresent()) {
        return ResponseEntity.ok().body(house.get());
      }
      else {
        throw new NoSuchElementException("No house found!");
      }
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreate(String cities) {
    try {
      Houses house = new Houses(
        houseRequest.getAddress()
      );
      houseRepository.save(house);
      return ResponseEntity.ok().body(house);
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
  
}
