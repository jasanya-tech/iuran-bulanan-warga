package com.iuran_bulanan_warga.Controllers.CRUD;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.ProvinceRequest;
import com.iuran_bulanan_warga.Services.ProvinceService;

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
@RequestMapping("api/provinces")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Provinces", description = "CRUD")

public class ProvinceController {
  @Autowired
  ProvinceService provinceService;

  @Operation(summary = "Get All Provinces", description = "Endpoint for getting all provinces")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return provinceService.serviceGetAll();
  }

  @Operation(summary = "Get One Province", description = "Endpoint for getting one province")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    return provinceService.serviceGetById(id);
  }

  @Operation(summary = "Create Province", description = "Endpoint for creating province")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody ProvinceRequest provinceRequest) {
    return provinceService.serviceCreate(provinceRequest);
  }

  @Operation(summary = "Update Province", description = "Enspoint for updating province")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @Valid @RequestBody ProvinceRequest provinceRequest,
      @PathVariable(value = "id", required = true) Integer id) {
    System.out.println(id);
    return provinceService.serviceUpdate(id, provinceRequest);
  }

  @Operation(summary = "Delete One Province", description = "Endpoint for deleting one province")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
    return provinceService.serviceDeleteById(id);
  }

  @Operation(summary = "Delete All Provinces", description = "Endpoint for deleting all provinces")
  @DeleteMapping
  public ResponseEntity<?> deleteAll() {
    return provinceService.serviceDeleteAll();
  }
}
