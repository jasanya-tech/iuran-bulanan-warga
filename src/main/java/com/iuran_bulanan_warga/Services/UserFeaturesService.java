package com.iuran_bulanan_warga.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iuran_bulanan_warga.Helpers.DTO.Repository.SearchByNameOutput;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

@Service
public class UserFeaturesService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> searchByFullName(String keyword) {
        try {
            List<SearchByNameOutput> searchByNameResponse = userRepository.searchByFullName(keyword);
            if (searchByNameResponse.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(searchByNameResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
