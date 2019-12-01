package com.trl.userservice.exceptions;

public class TheSameValueException extends RuntimeException {
    public TheSameValueException(String message) {
        super(message);
    }
}
