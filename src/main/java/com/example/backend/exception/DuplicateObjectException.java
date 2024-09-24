package com.example.backend.exception;

public class DuplicateObjectException extends RuntimeException{

    public DuplicateObjectException() {
    }

    public DuplicateObjectException(String message) {
        super(message);
    }
}
