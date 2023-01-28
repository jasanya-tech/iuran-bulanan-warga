package com.iuran_bulanan_warga.Services;

import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService {
  @Autowired
  HouseRepository houseRepository;

  // find one house by id
  // custome response in DTO
  // bikin new monthlyDues
  // monthlyDues.houseId.blablabla
  // for each di set total cost
}
