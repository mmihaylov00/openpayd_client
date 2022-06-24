package com.example.openpayd_client.dto.db;

import com.example.openpayd_client.enumeration.TransactionStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

public interface TransactionListDTO {
    BigDecimal getAmount();

    @Enumerated(EnumType.STRING)
    TransactionStatus getStatus();

    Date getCreatedAt();
}
