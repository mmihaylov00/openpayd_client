package com.example.openpayd_client.service;

import com.example.openpayd_client.auth.UserData;
import com.example.openpayd_client.dto.CurrencyAmountDTO;
import com.example.openpayd_client.dto.external.GetAccountResponseDTO;
import com.example.openpayd_client.dto.external.RegisterUserDataRequestDTO;
import com.example.openpayd_client.exception.HttpResponseException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;

public interface UserService extends UserDetailsService {
    void registerAccount(RegisterUserDataRequestDTO individual) throws HttpResponseException;

    CurrencyAmountDTO balance(String userId) throws HttpResponseException;
}
