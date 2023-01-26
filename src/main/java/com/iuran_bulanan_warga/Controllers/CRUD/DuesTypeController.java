package com.iuran_bulanan_warga.Controllers.CRUD;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.DuesTypeRequest;
import com.iuran_bulanan_warga.Services.DuesTypeService;

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
@RequestMapping("api/duesTypes")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Dues Type", description = "CRUD")

public class DuesTypeController {
  @Autowired
  DuesTypeService duesTypeService;

  @Operation(summary = "Get All Dues Type", description = "Endpoint for getting all dues type")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return duesTypeService.serviceGetAll();
  }

  @Operation(summary = "Get One Dues Type", description = "Endpoint for getting one dues type")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    return duesTypeService.serviceGetById(id);
  }

  @Operation(summary = "Create Dues Type", description = "Endpoint for creating dues type")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody DuesTypeRequest duesTypeRequest) {
    return duesTypeService.serviceCreate(duesTypeRequest);
  }

  @Operation(summary = "Update Dues Type", description = "Enspoint for updating dues type")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(
      @Valid @RequestBody DuesTypeRequest duesTypeRequest,
      @PathVariable(value = "id", required = true) Integer id) {
    System.out.println(id);
    return duesTypeService.serviceUpdate(id, duesTypeRequest);
  }

  @Operation(summary = "Delete One dues type", description = "Endpoint for deleting one dues type")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
    return duesTypeService.serviceDeleteById(id);
  }

  @Operation(summary = "Delete All Dues Type", description = "Endpoint for deleting all dues type")
  @DeleteMapping
  public ResponseEntity<?> deleteAll() {
    return duesTypeService.serviceDeleteAll();
  }
}
