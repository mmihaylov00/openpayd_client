package com.example.openpayd_client.dto.external;

public class CreateAccountRequestDTO {
    private final String currency = "EUR";
    private final String friendlyName = "Personal Account";

    public CreateAccountRequestDTO() {
    }

    public String getCurrency() {
        return currency;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
