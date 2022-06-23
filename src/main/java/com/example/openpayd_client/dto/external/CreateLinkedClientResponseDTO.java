package com.example.openpayd_client.dto.external;

import com.example.openpayd_client.dto.AddressDTO;
import com.example.openpayd_client.enumeration.ClientType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CreateLinkedClientResponseDTO {
    private String accountHolderId;

    public CreateLinkedClientResponseDTO() {
    }

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }
}
