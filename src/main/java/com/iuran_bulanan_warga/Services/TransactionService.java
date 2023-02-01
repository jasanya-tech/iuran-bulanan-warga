package com.iuran_bulanan_warga.Services;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.TransactionRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Requests.TransactionUpdateRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.BillingListUserResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.DetailTransactions;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.Transactions;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.DetailTransactionsRepository;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.TransactionRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

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
  DetailTransactionsRepository detailTransactionsRepository;

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
      List<Transactions> transactions = new ArrayList<Transactions>();
      transactionRequest.getHouseId().forEach(houseId -> {
        Integer diffMonth;
        Boolean lastTransaction = transactionRepository
            .findLastTransactionsByHouseId(Integer.parseInt(houseId));
        if (lastTransaction) {
          diffMonth = transactionRepository.findDiffMonth(Integer.parseInt(houseId));
        } else {
          diffMonth = houseRepository.findDiffMonth(Integer.parseInt(houseId));
        }
        Optional<Houses> house = houseRepository.findById(Integer.parseInt(houseId));
        Transactions transaction = new Transactions(
            house.get(),
            house.get().getOwner());
        house.get().getMonthlyDues().forEach(dues -> {
          DetailTransactions detailTransaction = new DetailTransactions();
          detailTransaction.setDuesName(dues.getDuesName());
          detailTransaction.setCost(dues.getCost());
          detailTransactionsRepository.save(detailTransaction);
          transaction.getDetailTransactions().add(detailTransaction);
          transaction.setTotalCost((transaction.getTotalCost() + Integer.parseInt(dues.getCost()) * diffMonth));
        });
        transactions.add(transaction);
      });
      transactionRepository.saveAll(transactions);
      return ResponseEntity.ok().body(transactions);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, TransactionUpdateRequest transactionUpdateRequest) {
    try {
      Optional<Transactions> transaction = transactionRepository.findById(id);
      Optional<Houses> house = houseRepository.findById(Integer.parseInt(transactionUpdateRequest.getHouseId()));
      Optional<Users> user = userRepository.findById(Integer.parseInt(transactionUpdateRequest.getUserId()));

      if (!transaction.isPresent()) {
        throw new NoSuchElementException("Transaction with ID " + id + " doesn't exist!");
      }

      Transactions transactionData = transaction.get();
      transactionData.setHouse(house.get());
      transactionData.setUserId(user.get());
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
      return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      transactionRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All transactions has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> billingListUser(Integer userId) {
    try {
      List<Houses> houses = houseRepository.findHousesByUserId(userId);
      if (houses.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      List<BillingListUserResponse> billingListUserResponses = new ArrayList<>();
      houses.forEach(house -> {
        BillingListUserResponse billingListUserResponse = new BillingListUserResponse();
        Boolean lastTransaction = transactionRepository
            .findLastTransactionsByHouseId(house.getId());
        if (lastTransaction) {
          Integer diffMonth = transactionRepository.findDiffMonth(house.getId());
          if (diffMonth != null) {
            billingListUserResponse.setNumBillMonths(diffMonth);
          }
        } else {
          Integer diffMonth = houseRepository.findDiffMonth(house.getId());
          if (diffMonth != null) {
            billingListUserResponse.setNumBillMonths(diffMonth);
          }
        }
        String address = house.getStreet() != null
            ? house.getStreet() + " NO." + house.getHouseNumber() + " RT " + house.getRt()
                + " / RW " + house.getRw()
            : "belum di setting";
        billingListUserResponse.setHouseId(house.getId());
        billingListUserResponse.setPictures(house.getPictures());
        billingListUserResponse.setHouseName(house.getHouseName());
        billingListUserResponse.setOwnerName(house.getOwner().getFullName());
        billingListUserResponse.setTotalOccupants(house.getOccupants().size());
        billingListUserResponse.setAddress(address);
        house.getMonthlyDues().forEach(dues -> {
          billingListUserResponse
              .setTotalCost(billingListUserResponse.getTotalCost() + Integer.parseInt(dues.getCost()));
        });
        billingListUserResponse
            .setTotalCost(billingListUserResponse.getTotalCost() * billingListUserResponse.getNumBillMonths());
        billingListUserResponses.add(billingListUserResponse);
      });
      return ResponseEntity.ok().body(billingListUserResponses);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
    }
  }
}
