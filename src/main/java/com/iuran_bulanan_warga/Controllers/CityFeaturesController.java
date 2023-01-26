package com.iuran_bulanan_warga.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Services.CityFeaturesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/cities/views")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "cities Features", description = "Additional features for managing the UI of cities data")
public class CityFeaturesController {

    @Autowired
    CityFeaturesService cityFeaturesService;

    @GetMapping("/page")
    @Operation(summary = "Show cities Data", description = "Showing the data of cities with pagination")
    public ResponseEntity<?> showCitiesWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return cityFeaturesService.showCitiesWithPage(page, size);
    }
}
