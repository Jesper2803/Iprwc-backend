package com.example.Iprwcbackend.module.Security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleInvalidArgument(MethodArgumentNotValidException exception){
        List<String> errorMessages = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessages.add(error.getDefaultMessage());
        });

        return errorMessages;
    }
}
