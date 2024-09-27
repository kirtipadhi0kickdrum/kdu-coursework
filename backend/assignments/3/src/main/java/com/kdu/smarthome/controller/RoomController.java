package com.kdu.smarthome.controller;


import com.kdu.smarthome.request.RoomRequest;
import com.kdu.smarthome.response.RoomResponse;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService)
    {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomResponse addRoom(@RequestParam Long houseId, @RequestBody RoomRequest roomRequest)
    {
        return roomService.addRoom(houseId, roomRequest);
    }
}
