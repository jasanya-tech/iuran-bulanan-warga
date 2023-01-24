package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Services.HouseFeaturesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/houses/features")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "House Features", description = "Additional features for managing the UI of houses data")
public class HouseFeaturesController {
  @Autowired
  HouseFeaturesService houseFeaturesService;

  @GetMapping("/{id}")
    @Operation(summary = "Show House Data", description = "Showing the specification of house")
    public ResponseEntity<?> showHouse(@PathParam("id") Integer houseId) {
      return houseFeaturesService.showHouseData(houseId);
    }
}
