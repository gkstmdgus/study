package com.example.hanghaeblog2.exception.customException;

public class DuplicatedIdException extends RuntimeException{
    public DuplicatedIdException(String message) {
        super(message);
    }

    public DuplicatedIdException() {
    }
}
