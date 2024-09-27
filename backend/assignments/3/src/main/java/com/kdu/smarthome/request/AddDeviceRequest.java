package com.kdu.smarthome.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDeviceRequest {
    private String houseId;
    private String roomId;
    private String kickstonId;

    public String getKickstoneId() {
        return kickstonId;
    }
}
