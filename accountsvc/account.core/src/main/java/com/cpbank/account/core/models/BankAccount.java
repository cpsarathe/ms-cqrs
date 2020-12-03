package com.cpbank.account.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "account")
@Entity
@Data
@Builder
@AllArgsConstructor
public class BankAccount {
    public BankAccount(){

    }
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "account_holder_id")
    private String accountHolderId;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "account_type")
    private AccountType accountType;
    @Column(name = "amount")
    private BigDecimal accountBalance;
}

