package com.iuran_bulanan_warga.Controllers.CRUD;

import com.iuran_bulanan_warga.Services.HouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Users", description = "CRUD")

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
  
}
