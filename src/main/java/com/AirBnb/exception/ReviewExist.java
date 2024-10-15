package com.AirBnb.exception;

public class ReviewExist extends RuntimeException{
    public ReviewExist(String message) {
        super(message);
    }
}
