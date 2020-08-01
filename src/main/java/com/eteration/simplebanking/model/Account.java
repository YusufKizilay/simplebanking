package com.eteration.simplebanking.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Account {

    private String owner;

    @Id
    private String accountNumber;

    private volatile double balance;

    @CreationTimestamp
    private Date createdDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Account() {
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException("Balance is insufficient!");
        }

        this.balance = this.balance - amount;
    }


    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.setAccount(this);

        if (TransactionType.DepositTransaction.equals(transaction.getType())) {
            deposit(transaction.getAmount());
        } else {
            withdraw(transaction.getAmount());
        }

        transaction.setApprovalCode(UUID.randomUUID().toString());

        transactions.add(transaction);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

}
