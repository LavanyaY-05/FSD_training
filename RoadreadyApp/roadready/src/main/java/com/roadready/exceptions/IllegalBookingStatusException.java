package com.roadready.exceptions;

public class IllegalBookingStatusException extends RuntimeException {
    public IllegalBookingStatusException(String message) {
        super(message);
    }
}
