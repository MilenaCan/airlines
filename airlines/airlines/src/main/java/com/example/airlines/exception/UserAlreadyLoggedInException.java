package com.example.airlines.exception;

public class UserAlreadyLoggedInException extends RuntimeException {
    public UserAlreadyLoggedInException(String message) {
        super(message);
    }
}
