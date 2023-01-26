package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    @Size(min = 3, max = 45)
    private String fullName;

    @Size(max = 13)
    private String phoneNumber;

    @NotBlank
    @Size(max = 128)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    private Set<String> role;
}
