package com.finalproject.springbootfoodapp.exception.handlers;

import com.finalproject.springbootfoodapp.exception.RepoException;
import com.finalproject.springbootfoodapp.exception.model.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RepoRequestExceptionHandler {

    @ExceptionHandler(value = {RepoException.class})
    public ResponseEntity<Object> handleRepoRequestException(RepoException e) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        RequestException requestException = new RequestException(e.getMessage(), badRequest);

        return new ResponseEntity<>(requestException, badRequest);
    }
}
