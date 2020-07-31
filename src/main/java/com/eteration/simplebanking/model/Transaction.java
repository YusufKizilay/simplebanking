package com.eteration.simplebanking.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public abstract class Transaction {

    private Date date;

    private double amount;

    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date=new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    protected abstract boolean isDeposit();
}
