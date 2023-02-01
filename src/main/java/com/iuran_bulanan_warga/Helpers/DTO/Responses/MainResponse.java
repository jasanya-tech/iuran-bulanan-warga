package com.iuran_bulanan_warga.Helpers.DTO.Responses;

import lombok.Data;

@Data
public class MainResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
