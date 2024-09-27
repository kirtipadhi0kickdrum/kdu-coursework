package com.kdu.smarthome.response;


import com.kdu.smarthome.dto.HouseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddHouseResponseDTO {

    private String message;
    private HouseDTO house;
}
