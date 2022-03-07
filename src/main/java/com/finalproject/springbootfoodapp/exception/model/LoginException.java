package com.finalproject.springbootfoodapp.exception.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LoginException {
    private final String message;
    private final HttpStatus httpStatus;

    public LoginException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
