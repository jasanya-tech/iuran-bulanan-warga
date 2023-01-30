package com.iuran_bulanan_warga.Controllers.CRUD;

import java.io.IOException;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.TransactionRequest;
import com.iuran_bulanan_warga.Services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/transactions")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Transactions", description = "CRUD")

public class TransactionController {
  @Autowired
  TransactionService transactionService;

  @Operation(summary = "Get All Transactions", description = "Endpoint for getting all transactions")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return transactionService.serviceGetAll();
  }

  @Operation(summary = "Get One Transaction", description = "Endpoint for getting one transaction by ID")
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    return transactionService.serviceGetById(id);
  }

  @Operation(summary = "Create Transaction", description = "Endpoint for creating new transaction")
  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody TransactionRequest transactionRequest) throws IOException {
    return transactionService.serviceCreate(transactionRequest);
  }
  
  @Operation(summary = "Update Transaction", description = "Endpoint for updating one transaction by ID")
  @PutMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Integer id,
    @Valid @RequestBody TransactionRequest transactionRequest) {
      return transactionService.serviceUpdate(id, transactionRequest);
  }

  @Operation(summary = "Delete One Transaction", description = "Endpoint for deleting one transaction by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOneById(@PathVariable("id") Integer id) {
    return transactionService.serviceDeleteById(id);
  }

  @Operation(summary = "Delete All Transaction", description = "Endpoint for deleting all transactions")
  @DeleteMapping
  public ResponseEntity<?> deleteAll() {
    return transactionService.serviceDeleteAll();
  }
}
