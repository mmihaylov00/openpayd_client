package com.example.openpayd_client.dto.external;

import com.example.openpayd_client.dto.CurrencyAmountDTO;

import java.math.BigDecimal;

public class GetAccountResponseDTO {
    private CurrencyAmountDTO actualBalance;

    public GetAccountResponseDTO() {
    }

    public CurrencyAmountDTO getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(CurrencyAmountDTO actualBalance) {
        this.actualBalance = actualBalance;
    }
}
