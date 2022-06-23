package com.example.openpayd_client.service.internal;

import com.example.openpayd_client.auth.UserData;
import com.example.openpayd_client.client.OpenpaydFeignClient;
import com.example.openpayd_client.dto.AddressDTO;
import com.example.openpayd_client.dto.CurrencyAmountDTO;
import com.example.openpayd_client.dto.external.*;
import com.example.openpayd_client.enumeration.ErrorCode;
import com.example.openpayd_client.exception.HttpResponseException;
import com.example.openpayd_client.model.UserModel;
import com.example.openpayd_client.repository.UserRepository;
import com.example.openpayd_client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OpenpaydFeignClient openpaydClient;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OpenpaydFeignClient openpaydClient) {
        this.userRepository = userRepository;
        this.openpaydClient = openpaydClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findFirstByEmail(username);
        if (user.isEmpty()) return null;

        UserModel userModel = user.get();

        return new UserData(userModel);
    }

    @Override
    public void registerAccount(RegisterUserDataRequestDTO individual) throws HttpResponseException {
        UserModel userModel = new UserModel(individual.getEmail(), individual.getPassword());
        userModel.setHolderId(createLinkedClient(individual));
        userModel.setAccountId(createAccount(userModel.getHolderId()));

       this.userRepository.save(userModel);
    }

    @Override
    public CurrencyAmountDTO balance(String userId) throws HttpResponseException {
        Optional<UserModel> user = this.userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty()) throw new HttpResponseException(ErrorCode.UNAUTHORIZED);
        UserModel userModel = user.get();

//        GetAccountResponseDTO account = this.openpaydClient.getAccount(userModel.getHolderId(), userModel.getAccountId());
//        return account.getActualBalance();

        return new CurrencyAmountDTO(userModel.getBalance());
    }

    private String createLinkedClient(RegisterUserDataRequestDTO individualDTO){
        CreateLinkedClientResponseDTO response = this.openpaydClient.createLinkedClient(
                new CreateLinkedClientRequestDTO(individualDTO)
        );
        return response.getAccountHolderId();
    }

    private String createAccount(String holderId){
        CreateAccountResponseDTO account = this.openpaydClient.createAccount(holderId, new CreateAccountRequestDTO());
        return account.getId();
    }
}
