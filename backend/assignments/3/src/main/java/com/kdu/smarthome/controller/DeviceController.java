package com.kdu.smarthome.controller;


import com.kdu.smarthome.request.AddDeviceRequest;
import com.kdu.smarthome.request.DeviceRegisterRequest;
import com.kdu.smarthome.response.AddDeviceResponse;
import com.kdu.smarthome.response.DeviceRegisterResponse;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService)
    {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<DeviceRegisterResponse> registerDevice(@RequestBody DeviceRegisterRequest request) {
        try {
            DeviceRegisterResponse response = deviceService.registerDevice(request);
            return new ResponseEntity<>(response, response.getHttpStatus());
        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeviceRegisterResponse("Internal server error", null, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AddDeviceResponse> addDevice(@RequestBody AddDeviceRequest request)
    {
        AddDeviceResponse response = deviceService.addDevice(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
