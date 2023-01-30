package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import java.util.HashSet;
import java.util.Set;

import com.iuran_bulanan_warga.Models.Entities.DuesType;

import lombok.Data;

@Data
public class MonthlyDuesDetailResponse {
    private Integer id;
    private Set<String> pictures = new HashSet<String>();
    private String houseName;
    private String owner;
    private String address;
    private String cityName;
    private String provinceName;
    private Set<DuesType> duesTypes;
    private int totalOccupants = 0;

}
