package com.iuran_bulanan_warga.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Services.UserFeaturesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/users/features")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "User Features", description = "fitur fitur yang dapat di lakukan users")
public class UserFeaturesController {

    @Autowired
    UserFeaturesService userFeaturesService;

    @PostMapping
    @Operation(summary = "Add occupants", description = "Menambahkan penghuni rumah")
    public ResponseEntity<?> addOccupants(@RequestParam(name = "userId", required = true) Integer userId,
            @RequestParam(name = "houseId", required = true) Integer houseId) {
        return userFeaturesService.addOccupantsService(userId, houseId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show House Data", description = "Showing the specification of house")
    public ResponseEntity<?> showHouse(@PathParam("id") Integer houseId) {
      return userFeaturesService.showHouseData(houseId);
    }
}
