package com.iuran_bulanan_warga.Services;

import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.MonthlyDuesResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TransactionService {
  @Autowired
  HouseRepository houseRepository;

  public ResponseEntity<?> sumTotalCost(Integer houseId) {
    Optional<Houses> house = houseRepository.findById(houseId);
    MonthlyDuesResponse monthlyDuesResponse = new MonthlyDuesResponse();
    // houseResponse.setOwnerName(house.get().getOwner().getFullName());
    monthlyDuesResponse.setOwnerName(house.get().getOwner().getFullName());
    house.getMonthlyDues().forEach(duesType -> {
      monthlyDuesResponse.setTotalCost(Integer.parseInt(duesType.getCost()) + monthlyDuesResponse.getTotalCost());
    });
    return ResponseEntity.ok().body(null);
  }
  // find one house by id
  // custome response in DTO
  // bikin new monthlyDues
  // monthlyDues.houseId.blablabla
  // for each di set total cost
}
