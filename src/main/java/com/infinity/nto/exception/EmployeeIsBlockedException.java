package com.infinity.nto.exception;

public class EmployeeIsBlockedException extends RuntimeException {
    public EmployeeIsBlockedException(String message) {
        super(message);
    }
}
