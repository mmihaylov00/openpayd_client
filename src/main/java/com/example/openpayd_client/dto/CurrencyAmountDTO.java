package com.example.openpayd_client.dto;

import java.math.BigDecimal;

public class CurrencyAmountDTO {
    private String currency = "EUR";
    private BigDecimal value;

    public CurrencyAmountDTO() {
    }

    public CurrencyAmountDTO(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
