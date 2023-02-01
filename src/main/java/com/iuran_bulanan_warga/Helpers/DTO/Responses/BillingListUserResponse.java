package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import java.util.Set;

import com.iuran_bulanan_warga.Models.Entities.ImageHouses;

import lombok.Data;

@Data
public class BillingListUserResponse {
    private Integer houseId;
    private Set<ImageHouses> pictures;
    private String houseName;
    private String ownerName;
    private Integer totalOccupants;
    private String address;
    private int numBillMonths = 0; // number of billing months
    private int totalCost = 0;
}
