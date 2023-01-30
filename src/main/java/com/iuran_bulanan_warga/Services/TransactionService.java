package com.iuran_bulanan_warga.Services;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.TransactionRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.Transactions;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.TransactionRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  @Autowired
  HouseRepository houseRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  UserRepository userRepository;

  // List All Transactions
  public ResponseEntity<?> serviceGetAll() {
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
  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<Transactions> transaction = transactionRepository.findById(id);
      if (!transaction.isPresent()) {
        throw new NoSuchElementException("Not Found Transaction");
      }
      return ResponseEntity.ok().body(transaction);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> serviceCreate(TransactionRequest transactionRequest) {
    try {
      Optional<Houses> house = houseRepository.findById(Integer.parseInt(transactionRequest.getHouseId()));
      Optional<Users> user = userRepository.findById(Integer.parseInt(transactionRequest.getUserId()));
      Transactions transaction = new Transactions(
        house.get(),
        user.get(),
        Integer.parseInt(transactionRequest.getTotalCost()),
        LocalDate.now()
      );
      transactionRepository.save(transaction);
      return ResponseEntity.ok().body(transaction);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreateManyByUserId(TransactionRequest transactionRequest, Integer userId) {
    try {
      List<Houses> houses = houseRepository.findHousesByUserId(userId);
      List<Transactions> transactions = new ArrayList<Transactions>();

      houses.forEach(house -> {
        Transactions transaction = new Transactions(
          house,
          house.getOwner(),
          Integer.parseInt(transactionRequest.getTotalCost()),
          LocalDate.now()
        );
        transactions.add(transaction);
      });
      transactionRepository.saveAll(transactions);
      return ResponseEntity.ok().body(transactions);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, TransactionRequest transactionRequest) {
    try {
      Optional<Transactions> transaction = transactionRepository.findById(id);
      Optional<Houses> house = houseRepository.findById(Integer.parseInt(transactionRequest.getHouseId()));
      Optional<Users> user = userRepository.findById(Integer.parseInt(transactionRequest.getUserId()));

      if (!transaction.isPresent()) {
        throw new NoSuchElementException("Transaction with ID " + id + " doesn't exist!");
      }

      Transactions transactionData = transaction.get();
      transactionData.setHouseId(house.get());
      transactionData.setUserId(user.get());
      transactionData.setTotalCost(Integer.parseInt(transactionRequest.getTotalCost()));
      transactionData.setDate(LocalDate.now());
      transactionRepository.save(transactionData);

      return ResponseEntity.ok().body(transaction);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteById(Integer id) {
    try {
      Optional<Transactions> transaction = transactionRepository.findById(id);

      if (!transaction.isPresent()) {
        throw new NoSuchElementException("Transaction with ID " + id + " doesn't exist!");
      }

      transactionRepository.deleteById(id);

      return ResponseEntity.ok().body(new MessageResponse("Transaction with ID " + id + " has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      transactionRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All transactions has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
