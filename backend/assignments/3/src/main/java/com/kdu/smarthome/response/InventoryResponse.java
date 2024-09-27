package com.kdu.smarthome.response;



import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InventoryResponse {
    private String inventory;
    private HttpStatus httpStatus;
}
