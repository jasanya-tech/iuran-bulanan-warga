package com.iuran_bulanan_warga.Controllers.CRUD;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.HouseRequest;
import com.iuran_bulanan_warga.Helpers.utils.ImageUtils;
import com.iuran_bulanan_warga.Services.HouseService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/houses")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Houses", description = "CRUD")

public class HouseController {
  @Autowired
  HouseService houseService;

  @Operation(summary = "Get All Houses", description = "Endpoint for getting all houses")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return houseService.serviceGetAll();
  }

  @Operation(summary = "Get One House", description = "Endpoint for getting one house")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    return houseService.serviceGetById(id);
  }

  @Operation(summary = "Create House", description = "Endpoint for creating house")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody HouseRequest houseRequest) throws IOException {
    return houseService.serviceCreate(houseRequest);
  }

  @Operation(summary = "Update House", description = "Enspoint for updating house")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @Valid @RequestBody HouseRequest houseRequest,
      @PathVariable(value = "id", required = true) Integer id) {
    System.out.println(id);
    return houseService.serviceUpdate(id, houseRequest);
  }

  @Operation(summary = "Delete One House", description = "Endpoint for deleting one house")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
    return houseService.serviceDeleteById(id);
  }

  @Operation(summary = "Delete All Houses", description = "Endpoint for deleting all houses")
  @DeleteMapping
  public ResponseEntity<?> deleteAll() {
    return houseService.serviceDeleteAll();
  }

  @PostMapping("/addOccupants")
  @Operation(summary = "Add occupants", description = "Menambahkan penghuni rumah")
  public ResponseEntity<?> addOccupants(@RequestParam(name = "userId", required = true) Integer userId,
      @RequestParam(name = "houseId", required = true) Integer houseId) {
    return houseService.addOccupantsService(userId, houseId);
  }

}
