package com.example.openpayd_client.dto.request;

import com.example.openpayd_client.dto.CurrencyAmountDTO;
import com.example.openpayd_client.enumeration.TransactionStatus;
import com.example.openpayd_client.enumeration.TransactionType;

public class OpenpaydWebhookRequestDTO {
    private String transactionId;
    private CurrencyAmountDTO totalAmount;
    private TransactionStatus status;
    private AccountInfoDTO sourceInfo;
    private AccountInfoDTO destinationInfo;
    private TransactionType type;

    public OpenpaydWebhookRequestDTO() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public CurrencyAmountDTO getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(CurrencyAmountDTO totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public AccountInfoDTO getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(AccountInfoDTO sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public AccountInfoDTO getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(AccountInfoDTO destinationInfo) {
        this.destinationInfo = destinationInfo;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public static class AccountInfoDTO {
        private String accountHolderId;

        public AccountInfoDTO() {
        }

        public String getAccountHolderId() {
            return accountHolderId;
        }

        public void setAccountHolderId(String accountHolderId) {
            this.accountHolderId = accountHolderId;
        }
    }
}
