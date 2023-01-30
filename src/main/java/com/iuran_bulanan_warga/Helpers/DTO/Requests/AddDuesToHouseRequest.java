package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import java.util.List;

import lombok.Data;

@Data
public class AddDuesToHouseRequest {
    private List<String> idDuesTypes;
}
