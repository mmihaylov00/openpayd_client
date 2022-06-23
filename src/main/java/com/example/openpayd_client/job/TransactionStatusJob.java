package com.example.openpayd_client.job;

import com.example.openpayd_client.client.OpenpaydFeignClient;
import com.example.openpayd_client.dto.external.TransactionResponseDTO;
import com.example.openpayd_client.model.TransactionModel;
import com.example.openpayd_client.repository.TransactionRepository;
import com.example.openpayd_client.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;
import java.util.UUID;

@Configuration
@EnableScheduling
public class TransactionStatusJob {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final TransactionRepository transactionRepository;
    private final OpenpaydFeignClient openpaydClient;

    @Autowired
    public TransactionStatusJob(TransactionRepository transactionRepository, OpenpaydFeignClient openpaydClient) {
        this.transactionRepository = transactionRepository;
        this.openpaydClient = openpaydClient;
    }

    @Scheduled(fixedRate = 30_000)
    public void updateTransactions() {
        this.logger.info("CHECKING STATUSES...");
        Set<TransactionModel> allWaitingTransactions = this.transactionRepository.findAllWaiting();
        for (TransactionModel transaction : allWaitingTransactions) {
            TransactionResponseDTO response = this.openpaydClient.getTransactionStatus(transaction.getUser().getHolderId(),
                    transaction.getTransactionId());
            if(response.getStatus() != transaction.getStatus())
                transaction.setStatus(response.getStatus());
        }
        this.transactionRepository.saveAll(allWaitingTransactions);
        this.logger.info("FINISHED UPDATING STATUSES...");
    }
}
