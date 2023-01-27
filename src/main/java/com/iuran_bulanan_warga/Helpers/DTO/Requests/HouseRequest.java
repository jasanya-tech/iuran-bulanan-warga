package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HouseRequest {

  @Size(max = 50)
  private String houseName;

  @NotBlank
  private String houseNumber;

  private String rt;

  private String rw;

  @NotBlank
  private String streat;

  // number of id owner
  private String owner;

  // number of city owner
  private String city;
}
