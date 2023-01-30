package com.iuran_bulanan_warga.Models.Entities;

import com.iuran_bulanan_warga.Helpers.Entities.TypePicture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ImageHouses")
public class ImageHouses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String fileName;

    @NotBlank
    private String mimeType;

    @Enumerated(EnumType.STRING)
    private TypePicture typePicture;

    @Lob
    @Column(name = "source", columnDefinition = "MEDIUMBLOB")
    private byte[] source;

    private String path;

    public ImageHouses(String fileName, String mimeType) {
        this.fileName = fileName;
        this.mimeType = mimeType;
    }
}
