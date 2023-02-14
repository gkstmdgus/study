package com.example.hanghaeblog2.exception.customException;

public class UnknownException extends RuntimeException{
    public UnknownException() {
    }

    public UnknownException(String message) {
        super(message);
    }
}
