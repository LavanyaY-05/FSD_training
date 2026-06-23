package com.roadready.exceptions;

import lombok.Getter;

@Getter
public class InvalidBookingDateException extends RuntimeException {
    private String field;
    public InvalidBookingDateException(String field, String message) {
        super(message);
        this.field = field;
    }
}
