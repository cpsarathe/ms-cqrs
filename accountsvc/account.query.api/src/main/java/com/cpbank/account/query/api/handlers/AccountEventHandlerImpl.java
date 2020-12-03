package com.cpbank.account.query.api.handlers;

import com.cpbank.account.core.events.AccountClosedEvent;
import com.cpbank.account.core.events.AccountOpenedEvent;
import com.cpbank.account.core.events.FundsDepositedEvent;
import com.cpbank.account.core.events.FundsWithdrawnEvent;
import com.cpbank.account.core.models.BankAccount;
import com.cpbank.account.query.api.repositories.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @EventHandler
    public void on(AccountOpenedEvent accountOpenedEvent) {
        BankAccount bankAccount = BankAccount.builder()
                .accountBalance(accountOpenedEvent.getBalance())
                .accountHolderId(accountOpenedEvent.getAccountHolderId())
                .accountType(accountOpenedEvent.getAccountType())
                .id(accountOpenedEvent.getId())
                .createdDate(accountOpenedEvent.getCreatedDate())
                .build();
        accountRepository.save(bankAccount);
    }

    @EventHandler
    @Override
    public void on(FundsDepositedEvent fundsDepositedEvent) {
        Optional<BankAccount> bankAccount = accountRepository.findById(fundsDepositedEvent.getId());
        if (!bankAccount.isPresent()) {
            return;
        }
        BigDecimal balance = bankAccount.get().getAccountBalance();
        balance  = balance.add(fundsDepositedEvent.getAmount());
        bankAccount.get().setAccountBalance(balance);
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(FundsWithdrawnEvent fundsWithdrawnEvent) {
        Optional<BankAccount> bankAccount = accountRepository.findById(fundsWithdrawnEvent.getId());
        if (!bankAccount.isPresent()) {
            return;
        }
        bankAccount.get().setAccountBalance(fundsWithdrawnEvent.getBalance());
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(AccountClosedEvent accountClosedEvent) {
        accountRepository.deleteById(accountClosedEvent.getId());
    }
}
