package com.app.exceptions;

public class VehicleUnavailableException extends  RuntimeException{
    public VehicleUnavailableException(String message) {
        super(message);
    }
}
