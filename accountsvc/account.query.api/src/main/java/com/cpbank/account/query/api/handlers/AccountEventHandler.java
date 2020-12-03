package com.cpbank.account.query.api.handlers;

import com.cpbank.account.core.events.AccountClosedEvent;
import com.cpbank.account.core.events.AccountOpenedEvent;
import com.cpbank.account.core.events.FundsDepositedEvent;
import com.cpbank.account.core.events.FundsWithdrawnEvent;

public interface AccountEventHandler {

    void on(AccountOpenedEvent accountOpenedEvent);

    void on(FundsDepositedEvent fundsDepositedEvent);

    void on(FundsWithdrawnEvent fundsWithdrawnEvent);

    void on(AccountClosedEvent accountClosedEvent);
}
