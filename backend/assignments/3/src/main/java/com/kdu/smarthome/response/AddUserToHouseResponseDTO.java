package com.kdu.smarthome.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class AddUserToHouseResponseDTO {

    private String message;
    private String additionalInformation;
    private HttpStatus httpStatus;
}
