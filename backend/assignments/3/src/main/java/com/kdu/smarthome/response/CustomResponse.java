package com.kdu.smarthome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse<T> {
    private String message;
    private T data;
    private HttpStatus httpStatus;

}
