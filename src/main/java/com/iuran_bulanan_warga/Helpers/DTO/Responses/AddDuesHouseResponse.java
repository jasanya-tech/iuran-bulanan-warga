package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import java.util.Set;

import com.iuran_bulanan_warga.Models.Entities.DuesType;

import lombok.Data;

@Data
public class AddDuesHouseResponse {
    private Set<DuesType> duesTypes;
    private Integer totalCost = 0;
}
