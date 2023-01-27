package com.iuran_bulanan_warga.Controllers;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Helpers.utils.ImageUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/loadPicture")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Images Features", description = "Additional features for load image")
public class ImageController {

    @GetMapping("/{fileCode}")
    public ResponseEntity<?> loadPicture(@PathVariable("fileCode") String fileCode) {
        ImageUtils imageUtils = new ImageUtils();

        Resource resource = null;
        try {
            resource = imageUtils.getFileAsResource(fileCode);

        } catch (IOException io) {
            return new ResponseEntity<>(io.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (resource == null) {
            return new ResponseEntity<>("Picture Not Found", HttpStatus.NOT_FOUND);
        } else {
            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        }
    }

}
