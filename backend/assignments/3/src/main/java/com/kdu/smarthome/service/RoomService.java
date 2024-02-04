package com.kdu.smarthome.service;


import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.entity.RoomEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.request.RoomRequest;
import com.kdu.smarthome.response.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository)
    {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
    }


    public RoomResponse addRoom(Long houseId, RoomRequest roomRequest)
    {
        HouseEntity houseEntity = houseRepository.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House not found"));

        RoomEntity newRoomEntity = new RoomEntity(roomRequest.getRoomName(), houseEntity);
        RoomEntity saveRoomEntity = roomRepository.save(newRoomEntity);

        RoomDTO roomDTO = mapRoomEntityToDTO(saveRoomEntity);
        RoomResponse response = new RoomResponse();
        response.setMessage("Room added successfully");
        response.setRoom(roomDTO);
        response.setHttpStatus(HttpStatus.OK);

        return response;
    }

    private RoomDTO mapRoomEntityToDTO(RoomEntity roomEntity)
    {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setRoomName(roomEntity.getRoomName());
        return roomDTO;
    }
}
