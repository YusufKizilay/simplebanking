package com.eteration.simplebanking.model;


public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    protected boolean isDeposit() {
        return false;
    }
}


