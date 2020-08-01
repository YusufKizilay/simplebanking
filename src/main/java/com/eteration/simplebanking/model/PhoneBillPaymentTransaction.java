package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PhoneBillPayment")
public class PhoneBillPaymentTransaction extends WithdrawalTransaction {

    private String payee;
    private String phoneNumber;

    public PhoneBillPaymentTransaction(String payee,String phoneNumber,double amount){
        super(amount);
        this.payee=payee;
        this.phoneNumber=phoneNumber;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
