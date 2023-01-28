package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import java.util.Set;

import com.iuran_bulanan_warga.Models.Entities.DuesType;
import com.iuran_bulanan_warga.Models.Entities.ImageHouses;

import lombok.Data;

@Data
public class MonthlyDuesDetailResponse {
    private Integer id;
    private Set<ImageHouses> pictures;
    private String houseName;
    private String owner;
    private String address;
    private String cityName;
    private String provinceName;
    private Set<DuesType> duesTypes;

}
