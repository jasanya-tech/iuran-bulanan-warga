package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import lombok.Data;

@Data
public class TransactionRequest {
  private String houseId;

  private String userId;

  private String totalCost;

  private String date;
}
