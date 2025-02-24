package com.infinity.nto.exception;

public class SelfChangeException extends RuntimeException {
    public SelfChangeException(String message) {
        super(message);
    }
}
