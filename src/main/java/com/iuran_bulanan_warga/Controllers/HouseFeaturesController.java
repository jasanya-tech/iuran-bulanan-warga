package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Services.HouseFeaturesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/houses/views")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Houses Features", description = "Additional features for managing the UI of houses data")
public class HouseFeaturesController {
  @Autowired
  HouseFeaturesService houseFeaturesService;

  @GetMapping("/{id}")
  @Operation(summary = "Show One House Data", description = "Showing owner name, city name, and province name of house")
  public ResponseEntity<?> showHouseByID(@PathParam("id") Integer houseId) {
    return houseFeaturesService.showHouseData(houseId);
  }

  @GetMapping("/page")
  @Operation(summary = "Show Houses Data", description = "Showing the data of houses with pagination")
  public ResponseEntity<?> showHousesWithPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {
    return houseFeaturesService.showHousesWithPage(page, size);
  }
}
