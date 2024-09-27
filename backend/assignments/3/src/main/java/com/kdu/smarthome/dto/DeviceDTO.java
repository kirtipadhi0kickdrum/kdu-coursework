package com.kdu.smarthome.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceDTO {
    private Long id;
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
}
