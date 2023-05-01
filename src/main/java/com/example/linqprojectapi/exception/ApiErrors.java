package com.example.linqprojectapi.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String errorMessage) {
        errors = Arrays.asList(errorMessage);
    }

}
