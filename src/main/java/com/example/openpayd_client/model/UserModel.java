package com.example.openpayd_client.model;

import com.example.openpayd_client.util.Encoder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String holderId;

    @Column
    private String accountId;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TransactionModel> transactions = new HashSet<>();

    public UserModel(String email, String password) {
        this.email = email;
        this.password = Encoder.encode(password);
    }

    public UserModel() {
    }

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("CLIENT");
        grantedAuthoritiesList.add(grantedAuthority);
        return grantedAuthoritiesList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<TransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionModel> transactions) {
        this.transactions = transactions;
    }
}
