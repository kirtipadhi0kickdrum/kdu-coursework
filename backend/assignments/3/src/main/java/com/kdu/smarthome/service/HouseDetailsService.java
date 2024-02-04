package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.DeviceDTO;
import com.kdu.smarthome.dto.HouseDetailsDTO;
import com.kdu.smarthome.dto.RoomDTO;
import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.response.HouseDetailsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HouseDetailsService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseDetailsService(HouseRepository houseRepository)
    {
        this.houseRepository = houseRepository;
    }

    public HouseDetailsResponse getHouseDetails(Long houseId)
    {
        Optional<HouseEntity> optionalHouseEntity = houseRepository.findById(houseId);

        if(optionalHouseEntity.isPresent())
        {
            HouseEntity houseEntity = optionalHouseEntity.get();
            HouseDetailsDTO houseDetailsDTO = buildHouseDetailsDTO(houseEntity);
            return new HouseDetailsResponse("House details retrieved successfully ", toJSON(houseDetailsDTO), HttpStatus.OK);
        }
        else {
            return new HouseDetailsResponse("House not found", null, HttpStatus.NOT_FOUND);
        }
    }

    private HouseDetailsDTO buildHouseDetailsDTO(HouseEntity houseEntity)
    {
        HouseDetailsDTO houseDetailsDTO = new HouseDetailsDTO();
        houseDetailsDTO.setHouseId(houseEntity.getId());
        houseDetailsDTO.setHouseName(houseEntity.getHouseName());
        houseDetailsDTO.setHouseAddress(houseEntity.getAddress());


        List<RoomDTO> roomDTOList = houseEntity.getRooms().stream()
                .map(room -> {
                    RoomDTO roomDTO = new RoomDTO();
                    roomDTO.setId(room.getId());
                    roomDTO.setRoomName(room.getRoomName());
                    return roomDTO;
                })
                .toList();

        houseDetailsDTO.setRooms(roomDTOList);

        List<DeviceDTO> deviceDTOList = houseEntity.getDevices().stream()
                .map(device ->
                {
                    return new DeviceDTO(device.getId(), device.getKickstonId(), device.getDeviceUsername(), device.getDevicePassword() );

                })
                .toList();

        houseDetailsDTO.setDevices(deviceDTOList);

        return houseDetailsDTO;
    }


    private String toJSON(Object object)
    {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }catch(JsonProcessingException e)
        {
            log.error("Error converting the object to JSON", e);
            return null;
        }
    }

}
