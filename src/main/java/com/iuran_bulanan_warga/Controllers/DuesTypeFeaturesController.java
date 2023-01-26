package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Services.DuesTypesFeaturesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/duesTypes/views")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "DuesTypes Features", description = "Additional features for managing the UI of DuesTypes data")
public class DuesTypeFeaturesController {
    @Autowired
    DuesTypesFeaturesService duesTypesFeaturesService;

    @GetMapping("/page")
    @Operation(summary = "Show DuesTypes Data", description = "Showing the data of DuesTypes with pagination")
    public ResponseEntity<?> showDuesTypesWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return duesTypesFeaturesService.showDuesTypesWithPage(page, size);
    }
}
