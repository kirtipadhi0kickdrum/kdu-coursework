package com.kdu.smarthome.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HouseDetailsDTO {
    private Long houseId;

    private String houseName;
    private String houseAddress;
    private List<RoomDTO> rooms;
    private List<DeviceDTO> devices;
}
