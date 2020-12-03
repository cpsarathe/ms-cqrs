package com.cpbank.account.query.api.repositories;

import com.cpbank.account.core.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount,String> {
}
