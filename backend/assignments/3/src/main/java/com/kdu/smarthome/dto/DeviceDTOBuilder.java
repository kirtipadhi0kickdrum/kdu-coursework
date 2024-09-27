package com.kdu.smarthome.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeviceDTOBuilder {
    private Long id;
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
}
