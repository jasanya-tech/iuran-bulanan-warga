package com.iuran_bulanan_warga.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iuran_bulanan_warga.Models.Entities.Cities;
import com.iuran_bulanan_warga.Models.Repositories.CityRepository;

@Service
public class CityFeaturesService {

    @Autowired
    CityRepository cityRepository;

    public ResponseEntity<?> showCitiesWithPage(int page, int size) {
        try {
            List<Cities> cities = new ArrayList<Cities>();

            Pageable paging = PageRequest.of(page, size);
            Page<Cities> pageCities;
            pageCities = cityRepository.findAll(paging);
            cities = pageCities.getContent();

            Map<String, Object> res = new HashMap<>();
            res.put("data", cities);
            res.put("size", size);
            res.put("currentPage", paging.getPageNumber());
            res.put("totalItems", pageCities.getTotalElements());
            res.put("totalPage", pageCities.getTotalPages());
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
