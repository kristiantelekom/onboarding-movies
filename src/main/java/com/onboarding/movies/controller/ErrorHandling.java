package com.onboarding.movies.controller;

import com.onboarding.movies.exception.ResourceNotFoundException;
import com.onboarding.movies.model.message.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundErrorHandling(ResourceNotFoundException ex){
        ExceptionMessage message = new ExceptionMessage();
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
