package com.example.openpayd_client.job;

import com.example.openpayd_client.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TransactionStatusJob {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionStatusJob(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Scheduled(fixedRate = 300_000)
    public void updateTransactions() {
        this.logger.info("CURRENCY GENERATING STARTED...");
        //todo check if status has been updated
    }
}
