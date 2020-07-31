package com.eteration.simplebanking.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    private String owner;

    private String accountNumber;

    private volatile double balance;

    private Date createdDate;

    @OneToMany(mappedBy ="account", fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new HashSet<>();

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if(this.balance<amount){
            throw new InsufficientBalanceException();
        }

        this.balance = this.balance - amount;
    }


    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.setAccount(this);

        if(transaction.isDeposit()){
            deposit(transaction.getAmount());
        }
        else{
            withdraw(transaction.getAmount());
        }

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
