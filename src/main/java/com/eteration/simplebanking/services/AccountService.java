package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.AccountNotFoundException;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(final Account account) {
        return accountRepository.save(account);
    }

    public Account findAccount(String accountId) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountId);

        if (!account.isPresent()) {
            throw new AccountNotFoundException(String.format("Account is not found for identifier %s",accountId));
        }

        return account.get();
    }
}
