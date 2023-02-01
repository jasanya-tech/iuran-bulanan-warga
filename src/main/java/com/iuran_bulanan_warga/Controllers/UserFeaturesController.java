package com.iuran_bulanan_warga.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Services.UserFeaturesService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Users Features", description = "Additional features for frontend manage users data")
public class UserFeaturesController {
    @Autowired
    UserFeaturesService userFeaturesService;

    @GetMapping("search/fullname")
    public ResponseEntity<?> serachByFullName(@RequestParam("keyword") String keyword) {
        return userFeaturesService.searchByFullName(keyword);
    }
}
