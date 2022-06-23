package com.example.openpayd_client.dto.external;

import com.example.openpayd_client.dto.CurrencyAmountDTO;

import java.math.BigDecimal;

public class CreateTransactionRequestDTO {
    private CurrencyAmountDTO amount;

    private String accountId;

    private String reference;

    private final String paymentType = "SEPA";

    private BeneficiaryDTO beneficiary;

    public CreateTransactionRequestDTO() {
    }

    public CreateTransactionRequestDTO(BigDecimal amount, String accountId, String reference,
                                       String bankAccountCountry, String iban, String firstName, String lastName) {
        this.amount = new CurrencyAmountDTO(amount);
        this.accountId = accountId;
        this.reference = reference;
        this.beneficiary = new BeneficiaryDTO(bankAccountCountry, iban, firstName, lastName);
    }

    public CurrencyAmountDTO getAmount() {
        return amount;
    }

    public void setAmount(CurrencyAmountDTO amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public BeneficiaryDTO getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDTO beneficiary) {
        this.beneficiary = beneficiary;
    }

    public static class BeneficiaryDTO {
        private String bankAccountCountry;
        private String iban;
        private final String customerType = "RETAIL";
        private String firstName;
        private String lastName;

        public BeneficiaryDTO() {
        }

        public BeneficiaryDTO(String bankAccountCountry, String iban, String firstName, String lastName) {
            this.bankAccountCountry = bankAccountCountry;
            this.iban = iban;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getBankAccountCountry() {
            return bankAccountCountry;
        }

        public void setBankAccountCountry(String bankAccountCountry) {
            this.bankAccountCountry = bankAccountCountry;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getCustomerType() {
            return customerType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
