package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;
}
