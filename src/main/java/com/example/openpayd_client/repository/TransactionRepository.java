package com.example.openpayd_client.repository;

import com.example.openpayd_client.dto.db.TransactionListDTO;
import com.example.openpayd_client.model.TransactionModel;
import com.example.openpayd_client.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {

    @Query("SELECT t FROM TransactionModel t WHERE " +
            "t.status <> com.example.openpayd_client.enumeration.TransactionStatus.FAILED AND " +
            "t.status <> com.example.openpayd_client.enumeration.TransactionStatus.COMPLETED")
    Set<TransactionModel> findAllWaiting();

    Page<TransactionListDTO> findAllByUser(UserModel user, Pageable pageable);

    Optional<TransactionModel> findByTransactionId(String transactionId);
}
