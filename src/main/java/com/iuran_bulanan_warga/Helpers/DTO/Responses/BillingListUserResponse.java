package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import lombok.Data;

@Data
public class BillingListUserResponse {
    private String houseName;
    private String ownerName;
    private Integer totalOccupants;
    private String address;
    private int numBillMonths = 0; // number of billing months
    private int totalCost = 0;
}
