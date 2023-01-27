package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import lombok.Data;

@Data
public class MonthlyDuesResponse {
  public String houseName;
  public String ownerName;
  public String adress;
  public String totalCost;
}
