package com.example.airlines.exception;

public class InvalidLoginDataException extends  RuntimeException {
    public InvalidLoginDataException(String message) {
        super(message);
    }
}
