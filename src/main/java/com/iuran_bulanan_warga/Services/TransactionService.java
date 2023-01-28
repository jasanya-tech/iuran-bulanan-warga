package com.iuran_bulanan_warga.Services;

import com.iuran_bulanan_warga.Models.Entities.Transactions;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.TransactionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionService {
  @Autowired
  HouseRepository houseRepository;

  @Autowired
  TransactionRepository transactionRepository;

  // List All Transactions
  public ResponseEntity<?> getAll() {
    try {
      List<Transactions> transactions = transactionRepository.findAll();
      if (transactions.isEmpty()) {
        throw new NoSuchElementException("Not Found Transaction");
      }
      return ResponseEntity.ok().body(transactions);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Get One Transaction by ID
  public ResponseEntity<?> getOne(Integer transactionId) {
    try {
      Optional<Transactions> transaction = transactionRepository.findById(transactionId);
      if (!transaction.isPresent()) {
        throw new NoSuchElementException("Not Found Transaction");
      }
      return ResponseEntity.ok().body(transaction);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
