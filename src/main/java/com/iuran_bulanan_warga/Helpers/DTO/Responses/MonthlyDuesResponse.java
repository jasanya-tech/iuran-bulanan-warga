package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import lombok.Data;

@Data
public class MonthlyDuesResponse {
  public Integer id;
  public String houseName;
  public String owner;
  public String address;
  public Integer totalCost = 0;
}
