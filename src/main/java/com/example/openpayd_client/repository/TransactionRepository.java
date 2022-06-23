package com.example.openpayd_client.repository;

import com.example.openpayd_client.enumeration.TransactionStatus;
import com.example.openpayd_client.model.TransactionModel;
import com.example.openpayd_client.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, Long> {

    @Query("SELECT t FROM TransactionModel t WHERE " +
            "t.status <> com.example.openpayd_client.enumeration.TransactionStatus.FAILED AND " +
            "t.status <> com.example.openpayd_client.enumeration.TransactionStatus.COMPLETED")
    Set<TransactionModel> findAllWaiting();
}
