package com.flashcardsapi.entities;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmationResult {
    private boolean confirmed;

    private String message;
}
