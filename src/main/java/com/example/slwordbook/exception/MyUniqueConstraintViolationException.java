package com.example.slwordbook.exception;

import jakarta.validation.ConstraintViolationException;
//名前を登録する際、重複した場合例外
public class MyUniqueConstraintViolationException extends ConstraintViolationException {
    public MyUniqueConstraintViolationException(String message) {
        super(message, null);
    }
}
