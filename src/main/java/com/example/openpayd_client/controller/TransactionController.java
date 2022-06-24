package com.example.openpayd_client.controller;

import com.example.openpayd_client.dto.external.CreateTransactionRequestDTO;
import com.example.openpayd_client.exception.HttpResponseException;
import com.example.openpayd_client.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(Principal userData,
                                             @RequestBody CreateTransactionRequestDTO data) {
        try {
            this.transactionService.createTransaction(userData.getName(), data);
        } catch (HttpResponseException e) {
            return e.getResponse();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> listTransactions(Principal userData,
                                              @RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int pageAmount) {
        try {
            return ResponseEntity.ok(this.transactionService.listTransactions(userData.getName(), page, pageAmount));
        } catch (HttpResponseException e) {
            return e.getResponse();
        }
    }
}
