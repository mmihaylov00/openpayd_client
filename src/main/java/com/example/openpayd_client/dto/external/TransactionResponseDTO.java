package com.example.openpayd_client.dto.external;

import com.example.openpayd_client.enumeration.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TransactionResponseDTO {
    private String transactionId;
    private TransactionStatus status;

    public TransactionResponseDTO() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
