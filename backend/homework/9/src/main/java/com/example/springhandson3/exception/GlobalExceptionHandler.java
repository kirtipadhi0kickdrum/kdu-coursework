package com.example.springhandson3.exception;
import com.example.springhandson3.exception.customexception.HandleBadRequest;
import com.example.springhandson3.exception.customexception.HandleResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HandleResourceNotFound.class)
    public ResponseEntity<String> handleResourceNotFoundException(HandleResourceNotFound ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HandleBadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HandleBadRequest ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
