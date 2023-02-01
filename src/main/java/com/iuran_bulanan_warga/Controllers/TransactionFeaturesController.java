package com.iuran_bulanan_warga.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Services.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/transactions")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Houses Features", description = "Additional features for managing the UI of houses data")
public class TransactionFeaturesController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/billingListUser/{userId}")
    public ResponseEntity<?> billingListUser(@PathVariable("userId") Integer userId) {
        return transactionService.billingListUser(userId);
    }
}
