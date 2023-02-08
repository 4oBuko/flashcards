package com.flashcardsapi.exceptions;

public class CustomParamMissedException extends RuntimeException {
    public CustomParamMissedException() {
    }

    public CustomParamMissedException(String message) {
        super(message);
    }
}
