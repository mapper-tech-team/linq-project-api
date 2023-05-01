package com.example.linqprojectapi.exception;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
