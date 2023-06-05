package com.flashcardsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyUsedCredentialsException extends RuntimeException{
    public AlreadyUsedCredentialsException(String message) {
        super(message);
    }
}
