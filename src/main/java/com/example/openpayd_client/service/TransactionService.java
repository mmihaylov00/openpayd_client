package com.example.openpayd_client.service;

import com.example.openpayd_client.dto.external.CreateTransactionRequestDTO;
import com.example.openpayd_client.dto.request.OpenpaydWebhookRequestDTO;
import com.example.openpayd_client.dto.response.TransactionListResponseDTO;
import com.example.openpayd_client.exception.HttpResponseException;

public interface TransactionService {
    void createTransaction(String userId, CreateTransactionRequestDTO data) throws HttpResponseException;
    TransactionListResponseDTO listTransactions(String userId, int page, int pageAmount) throws HttpResponseException;

    void handleWebhookRequest(OpenpaydWebhookRequestDTO data);
}
