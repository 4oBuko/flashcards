package com.flashcardsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomEntityNotFoundException extends RuntimeException {
    public CustomEntityNotFoundException() {
        super("Entity not found");
    }

    public CustomEntityNotFoundException(String message) {
        super(message);
    }
}
