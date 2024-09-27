package com.kdu.smarthome.response;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class InventoryResponseDTOForPOST {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}
