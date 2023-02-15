package com.example.hanghaeblog2.exception.customException;

public class InvalidValueException extends RuntimeException{
    public InvalidValueException() {
    }

    public InvalidValueException(String message) {
        super(message);
    }
}
