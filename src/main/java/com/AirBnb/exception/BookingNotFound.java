package com.AirBnb.exception;

public class BookingNotFound extends RuntimeException{
    public BookingNotFound(String message) {
        super(message);
    }
}
