package com.iuran_bulanan_warga.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.HouseRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Cities;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.CityRepository;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.ProvinceRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class HouseService {
  @Autowired
  HouseRepository houseRepository;

  @Autowired
  ProvinceRepository provinceRepository;

  @Autowired
  CityRepository cityRepository;

  @Autowired
  UserRepository userRepository;

  public ResponseEntity<?> serviceGetAll() {
    try {
      List<Houses> houses = houseRepository.findAll();
      if (houses.isEmpty()) {
        throw new NoSuchElementException("No house found!");
      }
      return ResponseEntity.ok().body(houses);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<Houses> house = houseRepository.findById(id);
      if (house.isPresent()) {
        return ResponseEntity.ok().body(house.get());
      } else {
        throw new NoSuchElementException("No house found!");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreate(HouseRequest houseRequest) {
    try {
      Optional<Users> owner = userRepository.findById(Integer.parseInt(houseRequest.getOwner()));
      Optional<Cities> city = cityRepository.findById(Integer.parseInt(houseRequest.getCity()));
      Houses house = new Houses(
          houseRequest.getHouseName(),
          houseRequest.getStreet(),
          Integer.parseInt(houseRequest.getHouseNumber()),
          houseRequest.getRt(),
          houseRequest.getRw(),
          owner.get(),
          city.get());
      houseRepository.save(house);
      return ResponseEntity.ok().body(house);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, HouseRequest houseRequest) {
    try {
      Optional<Houses> house = houseRepository.findById(id);
      Optional<Users> owner = userRepository.findById(Integer.parseInt(houseRequest.getOwner()));
      Optional<Cities> city = cityRepository.findById(Integer.parseInt(houseRequest.getCity()));
      if (!house.isPresent()) {
        throw new NoSuchElementException("House" + id + "doesn't exist!");
      }
      Houses houseData = house.get();
      houseData.setHouseName(houseRequest.getHouseName());
      houseData.setStreet(houseRequest.getStreet());
      houseData.setHouseNumber(Integer.parseInt(houseRequest.getHouseNumber()));
      houseData.setRt(houseRequest.getRt());
      houseData.setRw(houseRequest.getRw());
      houseData.setOwner(owner.get());
      houseData.setCity(city.get());
      houseRepository.save(houseData);
      return ResponseEntity.ok().body(house);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteById(Integer id) {
    try {
      Optional<Houses> house = houseRepository.findById(id);
      if (!house.isPresent()) {
        throw new NoSuchElementException("House " + id + " doesn't exist!");
      }
      houseRepository.deleteById(id);
      return ResponseEntity.ok().body(new MessageResponse("House " + id + " has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      houseRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All houses has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  // adding occupants to the house
  public ResponseEntity<?> addOccupantsService(Integer userId, Integer houseId) {
    try {
      Users user = userRepository.findById(userId).get();
      Houses house = houseRepository.findById(houseId).get();
      house.getOccupants().add(user);
      houseRepository.save(house);
      return ResponseEntity.ok().body(house);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
