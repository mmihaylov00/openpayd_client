package com.example.openpayd_client.service;

import com.example.openpayd_client.dto.CurrencyAmountDTO;
import com.example.openpayd_client.dto.external.CreateTransactionRequestDTO;
import com.example.openpayd_client.dto.external.RegisterUserDataRequestDTO;
import com.example.openpayd_client.exception.HttpResponseException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TransactionService {
    void createTransaction(String id, CreateTransactionRequestDTO data) throws HttpResponseException;
}
