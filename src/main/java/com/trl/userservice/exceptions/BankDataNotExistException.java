package com.trl.userservice.exceptions;

public class BankDataNotExistException extends RuntimeException {

    public BankDataNotExistException(String message) {
        super(message);
    }
}
