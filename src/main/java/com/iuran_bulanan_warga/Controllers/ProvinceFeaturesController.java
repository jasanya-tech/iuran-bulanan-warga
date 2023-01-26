package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Services.ProvinceFeaturesService;

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
@RequestMapping("api/provinces/views")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Provinces Features", description = "Additional features for managing the UI of provinces data")
public class ProvinceFeaturesController {
  @Autowired
  ProvinceFeaturesService provinceFeaturesService;

  @GetMapping("/page")
  @Operation(summary = "Show Provinces Data", description = "Showing the data of provinces with pagination")
  public ResponseEntity<?> showProvincesWithPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {
    return provinceFeaturesService.showProvincesWithPage(page, size);
  }
}
