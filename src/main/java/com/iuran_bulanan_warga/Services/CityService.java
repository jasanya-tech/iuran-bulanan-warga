package com.iuran_bulanan_warga.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.CityRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Cities;
import com.iuran_bulanan_warga.Models.Entities.Provinces;
import com.iuran_bulanan_warga.Models.Repositories.CityRepository;
import com.iuran_bulanan_warga.Models.Repositories.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {
  @Autowired
  CityRepository cityRepository;

  @Autowired
  ProvinceRepository provinceRepository;

  public ResponseEntity<?> serviceGetAll() {
    try {
      List<Cities> cities = cityRepository.findAll();
      if (cities.isEmpty()) {
        throw new NoSuchElementException("No city found!");
      }
      return ResponseEntity.ok().body(cities);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<Cities> city = cityRepository.findById(id);
      if (city.isPresent()) {
        return ResponseEntity.ok().body(city.get());
      } else {
        throw new NoSuchElementException("No city found!");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreate(CityRequest cityRequest) {
    try {
      Optional<Provinces> province = provinceRepository.findById(cityRequest.getProvince());
      Cities city = new Cities(
        cityRequest.getCityName(),
        province.get()
      );
      cityRepository.save(city);
      return ResponseEntity.ok().body(city);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, CityRequest cityRequest) {
    try {
      Optional<Cities> city = cityRepository.findById(id);
      Optional<Provinces> province = provinceRepository.findById(cityRequest.getProvince());
      if (!city.isPresent()) {
        throw new NoSuchElementException("City" + id + "doesn't exist!");
      }
      Cities cityData = city.get();
      cityData.setCityName(cityRequest.getCityName());
      cityData.setProvince(province.get());
      cityRepository.save(cityData);
      return ResponseEntity.ok().body(city);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteById(Integer id) {
    try {
      Optional<Cities> city = cityRepository.findById(id);
      if (!city.isPresent()) {
        throw new NoSuchElementException("City " + id + " doesn't exist!");
      }
      cityRepository.deleteById(id);
      return ResponseEntity.ok().body(new MessageResponse("City " + id + " has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      cityRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All cities has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
