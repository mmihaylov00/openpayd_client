package com.example.openpayd_client.service.internal;

import com.example.openpayd_client.client.OpenpaydFeignClient;
import com.example.openpayd_client.dto.external.CreateTransactionRequestDTO;
import com.example.openpayd_client.dto.external.CreateTransactionResponseDTO;
import com.example.openpayd_client.enumeration.ErrorCode;
import com.example.openpayd_client.exception.HttpResponseException;
import com.example.openpayd_client.model.TransactionModel;
import com.example.openpayd_client.model.UserModel;
import com.example.openpayd_client.repository.TransactionRepository;
import com.example.openpayd_client.repository.UserRepository;
import com.example.openpayd_client.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final OpenpaydFeignClient openpaydClient;
    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(OpenpaydFeignClient openpaydClient, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.openpaydClient = openpaydClient;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(String id, CreateTransactionRequestDTO data) throws HttpResponseException {
        Optional<UserModel> user = this.userRepository.findById(UUID.fromString(id));
        if (user.isEmpty()) throw new HttpResponseException(ErrorCode.UNAUTHORIZED);
        final UserModel userModel = user.get();

        CreateTransactionResponseDTO transaction = this.openpaydClient.createTransaction(id, data);
        //todo get the status from here https://apidocs.openpayd.com/reference/get-transaction-by-id
        TransactionModel transactionModel = new TransactionModel(transaction.getTransactionId(), data.getAmount().getValue(),
                userModel, "CREATED");
        this.transactionRepository.save(transactionModel);
    }
}
