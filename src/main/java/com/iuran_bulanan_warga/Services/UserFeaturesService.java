package com.iuran_bulanan_warga.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.HouseResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

@Service
public class UserFeaturesService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    UserRepository userRepository;

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
    public ResponseEntity<?> showHouseData(Integer houseId){
      try {
        Optional<Houses> house = houseRepository.findById(houseId);
        HouseResponse houseResponse = new HouseResponse();
        houseResponse.setAddress(house.get().getAddress());
        houseResponse.setOwnerName(house.get().getOwner().getFullName());
        houseResponse.setCityName(house.get().getCity().getCityName());
        houseResponse.setProvinceName(house.get().getProvince().getProvinceName());
        return ResponseEntity.ok().body(houseResponse);
      } catch (Exception e) {
        return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
      }
    }

}
