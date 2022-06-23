package com.example.openpayd_client.dto.external;

public class CreateTransactionResponseDTO {
    private String transactionId;

    public CreateTransactionResponseDTO() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
