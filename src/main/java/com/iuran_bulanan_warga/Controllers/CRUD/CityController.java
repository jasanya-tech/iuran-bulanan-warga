package com.iuran_bulanan_warga.Controllers.CRUD;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.CityRequest;
import com.iuran_bulanan_warga.Services.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/cities")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Cities", description = "CRUD")

public class CityController {
  @Autowired
  CityService cityService;

  @Operation(summary = "Get All Cities", description = "Endpoint for getting all cities")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return cityService.serviceGetAll();
  }

  @Operation(summary = "Get All Cities By Province id", description = "Endpoint for getting all cities by province id")
  @GetMapping("/province/{provinceId}")
  public ResponseEntity<?> getAllByProvince(@PathVariable("provinceId") Integer provinceId) {
    return cityService.serviceGetAllByProvince(provinceId);
  }

  @Operation(summary = "Get One City", description = "Endpoint for getting one city")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    return cityService.serviceGetById(id);
  }

  @Operation(summary = "Create City", description = "Endpoint for creating city")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody CityRequest cityRequest) {
    return cityService.serviceCreate(cityRequest);
  }

  @Operation(summary = "Update City", description = "Enspoint for updating city")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @Valid @RequestBody CityRequest cityRequest,
      @PathVariable(value = "id", required = true) Integer id) {
    System.out.println(id);
    return cityService.serviceUpdate(id, cityRequest);
  }

  @Operation(summary = "Delete One City", description = "Endpoint for deleting one city")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
    return cityService.serviceDeleteById(id);
  }

  @Operation(summary = "Delete All Cities", description = "Endpoint for deleting all cities")
  @DeleteMapping
  public ResponseEntity<?> deleteAll() {
    return cityService.serviceDeleteAll();
  }
}
