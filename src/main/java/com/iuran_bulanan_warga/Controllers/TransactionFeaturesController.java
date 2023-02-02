package com.iuran_bulanan_warga.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/transactions")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Transaction Features", description = "Additional features for managing the UI of tranactions data")
public class TransactionFeaturesController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/billingList/Users/{userId}")
    public ResponseEntity<?> billingListUser(@PathVariable("userId") Integer userId) {
        return transactionService.billingListUser(userId);
    }

    @GetMapping("/views/page")
    @Operation(summary = "Show Transactions Data", description = "Showing the data of transactions with pagination")
    public ResponseEntity<?> showTransactionsWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return transactionService.showTransactionsWithPage(page, size);
    }
}
