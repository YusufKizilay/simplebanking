package com.eteration.simplebanking.model;

public class InsufficientBalanceException extends BaseException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
