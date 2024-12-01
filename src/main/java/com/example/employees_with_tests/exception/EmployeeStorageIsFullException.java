package com.example.employees_with_tests.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
    }

    public EmployeeStorageIsFullException(String message) {
        super("storage is full");
    }
}
