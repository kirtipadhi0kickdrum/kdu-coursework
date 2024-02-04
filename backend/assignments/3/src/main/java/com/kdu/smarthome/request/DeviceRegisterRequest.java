package com.kdu.smarthome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRegisterRequest {
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private Long houseId;
    private Long roomId;

    public String getKickstoneId() {
        return kickstonId;
    }
}
