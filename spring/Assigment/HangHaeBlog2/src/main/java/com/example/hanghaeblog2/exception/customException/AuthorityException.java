package com.example.hanghaeblog2.exception.customException;

public class AuthorityException extends RuntimeException{
    public AuthorityException() {
    }

    public AuthorityException(String message) {
        super(message);
    }
}
