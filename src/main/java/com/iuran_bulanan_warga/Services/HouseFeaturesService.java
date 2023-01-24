package com.iuran_bulanan_warga.Services;

import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.HouseResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HouseFeaturesService {
  @Autowired
    HouseRepository houseRepository;
  
  public ResponseEntity<?> showHouseData(Integer houseId){
    try {
      Optional<Houses> house = houseRepository.findById(houseId);
      HouseResponse houseResponse = new HouseResponse();
      houseResponse.setAddress(house.get().getAddress());
      houseResponse.setOwnerName(house.get().getOwner().getFullName());
      houseResponse.setCityName(house.get().getCity().getCityName());
      houseResponse.setProvinceName(house.get().getCity().getProvince().getProvinceName());
      return ResponseEntity.ok().body(houseResponse);
    } catch (Exception e) {
      return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
    }
  }
}
