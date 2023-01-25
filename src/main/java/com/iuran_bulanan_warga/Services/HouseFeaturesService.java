package com.iuran_bulanan_warga.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.HouseResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    }
    catch (Exception e) {
      return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> showHousesWithPage(int page, int size) {
    try {
      List<Houses> houses = new ArrayList<Houses>();

      Pageable paging = PageRequest.of(page, size);
      Page<Houses> pageHouses;
      pageHouses = houseRepository.findAll(paging);
      houses = pageHouses.getContent();

      Map<String, Object> res = new HashMap<>();
      res.put("houses", houses);
      res.put("size", size);
      res.put("currentPage", paging.getPageNumber());
      res.put("totalItems", pageHouses.getTotalElements());
      res.put("totalPage", pageHouses.getTotalPages());
      return ResponseEntity.ok().body(res);
    }
    catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
