package com.cpbank.account.cmd.api.commands;

import com.cpbank.account.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class OpenBankAccountCommand {
    @TargetAggregateIdentifier
    private String id;
    private String  accountHolderId;
    private AccountType accountType;
    private BigDecimal balance;
}
