package com.example.openpayd_client.controller;

import com.example.openpayd_client.dto.request.OpenpaydWebhookRequestDTO;
import com.example.openpayd_client.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("openpayd-webhook")
public class OpenpaydWebhookController {
    private final TransactionService transactionService;

    @Autowired
    public OpenpaydWebhookController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody OpenpaydWebhookRequestDTO data) {
        this.transactionService.handleWebhookRequest(data);
        return ResponseEntity.ok().build();
    }
}
