package com.kdu.smarthome.exception;

public class HouseNotFoundException extends RuntimeException{
    public HouseNotFoundException(String message)
    {
        super(message);
    }
}
