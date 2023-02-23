package com.flashcardsapi.exceptions;

public class AlreadyUsedCredentialsException extends RuntimeException{
    public AlreadyUsedCredentialsException(String message) {
        super(message);
    }
}
