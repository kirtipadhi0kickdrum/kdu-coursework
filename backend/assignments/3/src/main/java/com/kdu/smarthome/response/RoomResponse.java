package com.kdu.smarthome.response;


import com.kdu.smarthome.dto.RoomDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RoomResponse {
    private String message;
    private RoomDTO room;
    private HttpStatus httpStatus;
}
