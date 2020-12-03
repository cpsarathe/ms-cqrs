package com.cpbank.account.cmd.api.aggregates;

import com.cpbank.account.cmd.api.commands.CloseAccountCommand;
import com.cpbank.account.cmd.api.commands.DepositFundsCommand;
import com.cpbank.account.cmd.api.commands.OpenBankAccountCommand;
import com.cpbank.account.cmd.api.commands.WithdrawFundsCommand;
import com.cpbank.account.core.events.AccountClosedEvent;
import com.cpbank.account.core.events.AccountOpenedEvent;
import com.cpbank.account.core.events.FundsDepositedEvent;
import com.cpbank.account.core.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String acocuntHolderId;
    private BigDecimal balance;

    @CommandHandler
    public AccountAggregate(OpenBankAccountCommand openBankAccountCommand) {
        AccountOpenedEvent accountOpenedEvent = AccountOpenedEvent.builder()
                .id(openBankAccountCommand.getId())
                .accountHolderId(openBankAccountCommand.getAccountHolderId())
                .accountType(openBankAccountCommand.getAccountType())
                .createdDate(new Date())
                .balance(openBankAccountCommand.getBalance())
                .build();
        AggregateLifecycle.apply(accountOpenedEvent);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent accountOpenedEvent) {
        this.id = accountOpenedEvent.getId();
        this.acocuntHolderId = accountOpenedEvent.getAccountHolderId();
        this.balance = accountOpenedEvent.getBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand depositFundsCommand) {
        FundsDepositedEvent fundsDepositedEvent = FundsDepositedEvent.builder()
                .id(depositFundsCommand.getId())
                .amount(depositFundsCommand.getAmount())
                .balance(depositFundsCommand.getAmount())
                .build();
        AggregateLifecycle.apply(fundsDepositedEvent);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent fundsDepositedEvent) {
        this.balance = this.balance.add(fundsDepositedEvent.getBalance());
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand withdrawFundsCommand) {
        BigDecimal balance = this.balance.subtract(withdrawFundsCommand.getAmount());
        if (balance.compareTo(BigDecimal.ZERO) < -1) {
            throw new IllegalStateException("withdrawl declined , insufficients fund");
        }
        FundsWithdrawnEvent fundsWithdrawnEvent = FundsWithdrawnEvent.builder()
                .id(withdrawFundsCommand.getId())
                .amount(withdrawFundsCommand.getAmount())
                .balance(this.balance.subtract(withdrawFundsCommand.getAmount()))
                .build();
        AggregateLifecycle.apply(fundsWithdrawnEvent);
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent fundsWithdrawnEvent) {
        this.balance = this.balance.subtract(fundsWithdrawnEvent.getAmount());
    }

    @CommandHandler
    public void handle(CloseAccountCommand closeAccountCommand) {
        AccountClosedEvent accountClosedEvent = AccountClosedEvent.builder()
                .id(closeAccountCommand.getId())
                .build();
        AggregateLifecycle.apply(accountClosedEvent);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent accountClosedEvent) {
        AggregateLifecycle.markDeleted();
    }

}
