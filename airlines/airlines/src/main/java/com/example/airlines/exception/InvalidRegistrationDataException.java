package com.example.airlines.exception;

public class InvalidRegistrationDataException extends RuntimeException {
    public InvalidRegistrationDataException(String message){
        super(message);
    }
}
