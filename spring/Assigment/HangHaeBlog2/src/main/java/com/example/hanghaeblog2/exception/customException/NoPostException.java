package com.example.hanghaeblog2.exception.customException;

public class NoPostException extends RuntimeException{
    public NoPostException() {
    }

    public NoPostException(String message) {
        super(message);
    }
}
