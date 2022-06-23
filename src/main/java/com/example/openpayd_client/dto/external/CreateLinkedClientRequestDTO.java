package com.example.openpayd_client.dto.external;

import com.example.openpayd_client.dto.AddressDTO;
import com.example.openpayd_client.enumeration.ClientType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CreateLinkedClientRequestDTO {
    @Enumerated(EnumType.STRING)
    private ClientType clientType = ClientType.INDIVIDUAL;

    private RegisterUserDataRequestDTO individual;

    public CreateLinkedClientRequestDTO(RegisterUserDataRequestDTO individual) {
        this.individual = individual;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public RegisterUserDataRequestDTO getIndividual() {
        return individual;
    }

    public void setIndividual(RegisterUserDataRequestDTO individual) {
        this.individual = individual;
    }
}
