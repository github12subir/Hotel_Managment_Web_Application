package com.AirBnb.exception;

public class PropertyNotFound extends RuntimeException{
    public PropertyNotFound(String message) {
        super(message);
    }
}
