package com.AirBnb.exception;

public class ReviewNotExist extends RuntimeException {
    public ReviewNotExist(String message) {
        super(message);
    }
}
