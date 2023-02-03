package com.iuran_bulanan_warga.Helpers.DTO.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchByNameOutput {
    private Integer id;
    private String fullName;
}
