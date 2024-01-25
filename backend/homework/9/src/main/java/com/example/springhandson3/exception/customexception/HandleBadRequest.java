package com.example.springhandson3.exception.customexception;


public class HandleBadRequest extends org.apache.coyote.BadRequestException{
    public HandleBadRequest(String message)
    {
        super(message);
    }
}
