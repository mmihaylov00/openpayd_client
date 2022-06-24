package com.example.openpayd_client.service.internal;

import com.example.openpayd_client.client.OpenpaydFeignClient;
import com.example.openpayd_client.dto.db.TransactionListDTO;
import com.example.openpayd_client.dto.external.CreateTransactionRequestDTO;
import com.example.openpayd_client.dto.external.TransactionResponseDTO;
import com.example.openpayd_client.dto.request.OpenpaydWebhookRequestDTO;
import com.example.openpayd_client.dto.response.TransactionListResponseDTO;
import com.example.openpayd_client.enumeration.ErrorCode;
import com.example.openpayd_client.enumeration.TransactionType;
import com.example.openpayd_client.exception.HttpResponseException;
import com.example.openpayd_client.model.TransactionModel;
import com.example.openpayd_client.model.UserModel;
import com.example.openpayd_client.repository.TransactionRepository;
import com.example.openpayd_client.repository.UserRepository;
import com.example.openpayd_client.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void createTransaction(String userId, CreateTransactionRequestDTO data) throws HttpResponseException {
        Optional<UserModel> user = this.userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty()) throw new HttpResponseException(ErrorCode.UNAUTHORIZED);
        final UserModel userModel = user.get();

        data.setAccountId(userModel.getAccountId());

        TransactionResponseDTO transaction = this.openpaydClient.createTransaction(userModel.getHolderId(), data);
        TransactionModel transactionModel = new TransactionModel(transaction.getTransactionId(), data.getAmount().getValue(),
                userModel, transaction.getStatus(), TransactionType.PAYOUT);
        this.transactionRepository.save(transactionModel);
    }

    @Override
    public TransactionListResponseDTO listTransactions(String userId, int page, int pageAmount) throws HttpResponseException {
        Optional<UserModel> user = this.userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty()) throw new HttpResponseException(ErrorCode.UNAUTHORIZED);
        final UserModel userModel = user.get();

        TransactionListResponseDTO transactionListResponseDTO = new TransactionListResponseDTO(page);

        Page<TransactionListDTO> transactions = this.transactionRepository.findAllByUser(userModel,
                PageRequest.of(page, pageAmount, Sort.by("createdAt").descending()));

        transactionListResponseDTO.setMaxPage(transactions.getTotalPages() - 1);

        transactionListResponseDTO.setTransactions(transactions.getContent());

        return transactionListResponseDTO;
    }

    @Override
    public void handleWebhookRequest(OpenpaydWebhookRequestDTO data) {
        Optional<TransactionModel> transaction = this.transactionRepository.findByTransactionId(data.getTransactionId());
        if (transaction.isPresent()) {
            TransactionModel transactionModel = transaction.get();
            transactionModel.setStatus(data.getStatus());
            this.transactionRepository.save(transactionModel);
            return;
        }

        //create a new transaction in db
        switch (data.getType()) {
            case TRANSFER:
            case PAYOUT: {
                Optional<UserModel> sender = this.userRepository.findFirstByHolderId(data.getSourceInfo().getAccountHolderId());
                //sender is not registered in the system
                if (sender.isEmpty()) return;

                TransactionModel transactionModel = new TransactionModel(data.getTransactionId(), data.getTotalAmount().getValue().abs(),
                        sender.get(), data.getStatus(), data.getType());
                this.transactionRepository.save(transactionModel);
                break;
            }
        }
    }
}
