package com.infinity.nto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CodeNotFoundException.class)
    ResponseEntity<String> codeNotFoundExceptionHandler(CodeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    ResponseEntity<String> employeeNotFoundExceptionHandler(EmployeeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmployeeDataNotFoundException.class)
    ResponseEntity<String> employeeDataNotFoundExceptionHandler(EmployeeDataNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeIsBlockedException.class)
    ResponseEntity<String> employeeIsBlockedExceptionHandler(EmployeeIsBlockedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }

    @ExceptionHandler(SelfChangeException.class)
    ResponseEntity<String> selfChangeExceptionHandler(SelfChangeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
