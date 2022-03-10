package com.finalproject.springbootfoodapp.exception.handlers;

import com.finalproject.springbootfoodapp.exception.model.RegisterException;
import com.finalproject.springbootfoodapp.exception.UserRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRegisterExceptionHandler {

    @ExceptionHandler(value = {UserRegisterException.class})
    public ResponseEntity<Object> handleUserRegisterException(UserRegisterException e) {

        // 1. Create payload containing exception details
        final HttpStatus badRequest = HttpStatus.SEE_OTHER;
        RegisterException registerException = new RegisterException(e.getMessage(), badRequest);

        // 2. Return response entity
        return new ResponseEntity<>(registerException, badRequest);
    }
}
