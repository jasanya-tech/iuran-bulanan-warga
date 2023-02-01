package com.iuran_bulanan_warga.Helpers.DTO.Requests;

import lombok.Data;

@Data
public class TransactionUpdateRequest {
    private String houseId;
    private String userId;
}
