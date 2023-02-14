package com.example.hanghaeblog2.exception.customException;

public class TokenException extends RuntimeException{
    public TokenException() {}
    public TokenException(String message) {
        super(message);
    }
}
