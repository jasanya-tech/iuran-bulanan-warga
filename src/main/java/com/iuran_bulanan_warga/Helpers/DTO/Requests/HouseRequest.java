package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HouseRequest {
  @NotBlank
  @Size(max = 255)
  private String address;
}
