package com.cpbank.account.core.events;

import com.cpbank.account.core.models.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class FundsDepositedEvent {
    @TargetAggregateIdentifier
    private String id;
    private BigDecimal balance;
    private BigDecimal amount;
}
