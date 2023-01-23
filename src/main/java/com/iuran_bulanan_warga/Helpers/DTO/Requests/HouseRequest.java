package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HouseRequest {
  @NotBlank
  @Size(max = 255)
  private String address;

  private Set<String> owner;

  private Set<String> city;

  private Set<String> province;
}
