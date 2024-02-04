package com.kdu.smarthome.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AddDeviceResponse {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}
