package com.cpbank.account.cmd.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawFundsCommand {
    @TargetAggregateIdentifier
    private String id;
    private BigDecimal amount;
}
