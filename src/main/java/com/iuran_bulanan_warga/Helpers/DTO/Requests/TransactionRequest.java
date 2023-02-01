package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import java.util.List;

import lombok.Data;

@Data
public class TransactionRequest {
  private List<String> houseId;
}
