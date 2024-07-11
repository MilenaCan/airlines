package com.example.airlines.exception;

public class InvalidReservationDataException extends RuntimeException {
    public InvalidReservationDataException(String message) {
        super(message);
    }
}
