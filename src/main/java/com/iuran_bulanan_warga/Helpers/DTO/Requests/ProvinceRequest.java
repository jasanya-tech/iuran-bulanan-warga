package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProvinceRequest {
  @NotBlank
  private String provinceName;
}
