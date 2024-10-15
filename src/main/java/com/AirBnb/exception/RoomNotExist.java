package com.AirBnb.exception;

public class RoomNotExist extends RuntimeException{
    public RoomNotExist(String message) {
        super(message);
    }
}
