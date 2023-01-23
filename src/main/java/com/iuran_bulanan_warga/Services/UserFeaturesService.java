package com.iuran_bulanan_warga.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

@Service
public class UserFeaturesService {

    @Autowired
    HouseRepository HouseRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> addOccupantsService(Integer userId, Integer houseId) {
        try {
            Set<Users> users = new HashSet<>();
            Users user = userRepository.findById(userId).get();
            Houses house = HouseRepository.findById(houseId).get();
            users.add(user);
            house.setOccupants(users);
            HouseRepository.save(house);
            return ResponseEntity.ok().body(house);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

}
