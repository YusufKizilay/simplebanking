package com.eteration.simplebanking.model;



public class DepositTransaction extends Transaction  {

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    protected boolean isDeposit() {
        return true;
    }

}
