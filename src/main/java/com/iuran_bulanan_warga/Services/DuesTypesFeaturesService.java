package com.iuran_bulanan_warga.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iuran_bulanan_warga.Models.Entities.DuesType;
import com.iuran_bulanan_warga.Models.Repositories.DuesTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DuesTypesFeaturesService {
    @Autowired
    DuesTypeRepository duesTypeRepository;

    public ResponseEntity<?> showDuesTypesWithPage(int page, int size) {
        try {
            List<DuesType> duesTypes = new ArrayList<DuesType>();

            Pageable paging = PageRequest.of(page, size);
            Page<DuesType> pageDuesTypes;
            pageDuesTypes = duesTypeRepository.findAll(paging);
            duesTypes = pageDuesTypes.getContent();

            Map<String, Object> res = new HashMap<>();
            res.put("data", duesTypes);
            res.put("size", size);
            res.put("currentPage", paging.getPageNumber());
            res.put("totalItems", pageDuesTypes.getTotalElements());
            res.put("totalPage", pageDuesTypes.getTotalPages());
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
