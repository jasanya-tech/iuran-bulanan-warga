package com.iuran_bulanan_warga.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iuran_bulanan_warga.Helpers.utils.ImageUtils;
import com.iuran_bulanan_warga.Models.Entities.ImageHouses;
import com.iuran_bulanan_warga.Models.Repositories.ImageHouseRepository;

@Service
public class ImageService {
    @Autowired
    ImageHouseRepository imageHouseRepository;

    public ResponseEntity<?> downloadImage(String fileName) {
        try {
            Optional<ImageHouses> picture = imageHouseRepository.findByFileName(fileName);
            byte[] images = ImageUtils.decompressImage(picture.get().getSource());
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(images);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
