package com.example.linqprojectapi.controller;

import com.example.linqprojectapi.exception.ApiErrors;
import com.example.linqprojectapi.exception.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlerCustomNotFoundException(CustomNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

}
