package com.finalproject.springbootfoodapp.exception.handlers;

import com.finalproject.springbootfoodapp.exception.model.LoginException;
import com.finalproject.springbootfoodapp.exception.UserLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserLoginExceptionHandler {

    @ExceptionHandler(value = {UserLoginException.class})
    public ResponseEntity<Object> handleUserRegisterException(UserLoginException e) {

        // 1. Create payload containing exception details
        final HttpStatus badRequest = HttpStatus.SEE_OTHER;
        LoginException loginException = new LoginException(e.getMessage(), badRequest);

        // 2. Return response entity
        return new ResponseEntity<>(loginException, badRequest);
    }
}
