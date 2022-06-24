package com.example.openpayd_client.dto.response;

import com.example.openpayd_client.dto.db.TransactionListDTO;

import java.util.List;

public class TransactionListResponseDTO {
    private int currentPage;
    private int maxPage;
    private List<TransactionListDTO> transactions;

    public TransactionListResponseDTO(int currentPage) {
        this.currentPage = currentPage;
    }

    public TransactionListResponseDTO() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<TransactionListDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionListDTO> transactions) {
        this.transactions = transactions;
    }
}
