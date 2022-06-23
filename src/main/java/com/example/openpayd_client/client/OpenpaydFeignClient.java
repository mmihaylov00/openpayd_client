package com.example.openpayd_client.client;

import com.example.openpayd_client.client.config.OAuthFeignConfig;
import com.example.openpayd_client.dto.external.*;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${api.openpayd.url}", name = "openpayd-client",
        configuration = OAuthFeignConfig.class)
public interface OpenpaydFeignClient {
    String ACCOUNT_HOLDER_ID = "ea2b2e06-090a-4c46-b8e5-a5d5ca46902f";
    String ACCOUNT_HOLDER_ID_KEY = "x-account-holder-id";

    @RequestMapping(method = RequestMethod.POST,
            value = "/linkedClient",
            consumes = "application/json")
    @Headers(ACCOUNT_HOLDER_ID_KEY + ": " + ACCOUNT_HOLDER_ID)
    CreateLinkedClientResponseDTO createLinkedClient(CreateLinkedClientRequestDTO body);

    @RequestMapping(method = RequestMethod.POST,
            value = "/accounts",
            consumes = "application/json")
    CreateAccountResponseDTO createAccount(@RequestHeader(ACCOUNT_HOLDER_ID_KEY) String holderId,
                                           CreateAccountRequestDTO body);

    @RequestMapping(method = RequestMethod.GET,
            value = "/accounts/{id}",
            consumes = "application/json")
    GetAccountResponseDTO getAccount(@RequestHeader(ACCOUNT_HOLDER_ID_KEY) String holderId,
                                     @PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST,
            value = "/transactions/beneficiaryPayout",
            consumes = "application/json")
    CreateTransactionResponseDTO createTransaction(@RequestHeader(ACCOUNT_HOLDER_ID_KEY) String holderId,
                                            CreateTransactionRequestDTO body);
}
