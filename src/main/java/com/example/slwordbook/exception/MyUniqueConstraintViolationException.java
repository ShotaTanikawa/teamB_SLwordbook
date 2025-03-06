package com.example.slwordbook.exception;

import jakarta.validation.ConstraintViolationException;

public class MyUniqueConstraintViolationException extends ConstraintViolationException {
    public MyUniqueConstraintViolationException(String message) {
        super(message, null);
    }
}
