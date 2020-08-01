package com.eteration.simplebanking.controller;


import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account accountToSave) {
        accountToSave.setAccountNumber(UUID.randomUUID().toString());
        Account account = accountService.saveAccount(accountToSave);

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/credit/{accountNo}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNo, @RequestBody DepositTransaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNo);

        account.post(transaction);

        accountService.saveAccount(account);

        return new ResponseEntity<>(new TransactionStatus(HttpStatus.OK.name(), transaction.getApprovalCode()), HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNo}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNo, @RequestBody WithdrawalTransaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = accountService.findAccount(accountNo);

        account.post(transaction);

        accountService.saveAccount(account);

        return new ResponseEntity<>(new TransactionStatus(HttpStatus.OK.name(), transaction.getApprovalCode()), HttpStatus.OK);
    }


    @GetMapping("/{accountNo}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNo) throws AccountNotFoundException {
        Account account = accountService.findAccount(accountNo);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}