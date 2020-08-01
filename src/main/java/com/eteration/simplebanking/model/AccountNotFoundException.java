package com.eteration.simplebanking.model;

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
