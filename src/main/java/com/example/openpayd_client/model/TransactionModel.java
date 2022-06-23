package com.example.openpayd_client.model;

import com.example.openpayd_client.enumeration.TransactionStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class TransactionModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserModel user;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private TransactionStatus status;

    public TransactionModel() {
    }

    public TransactionModel(String transactionId, BigDecimal amount, UserModel user, TransactionStatus status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
