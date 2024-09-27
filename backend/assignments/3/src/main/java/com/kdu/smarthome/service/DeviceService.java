package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.DeviceDTOBuilder;
import com.kdu.smarthome.entity.DeviceEntity;
import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.entity.RoomEntity;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.request.AddDeviceRequest;
import com.kdu.smarthome.request.DeviceRegisterRequest;
import com.kdu.smarthome.response.AddDeviceResponse;
import com.kdu.smarthome.response.DeviceRegisterResponse;
import com.kdu.smarthome.utility.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, HouseRepository houseRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }



    public DeviceRegisterResponse registerDevice(DeviceRegisterRequest request) {
        try {
            HouseEntity houseEntity = validateAndGetHouse(request.getHouseId());
            RoomEntity roomEntity = validateAndGetRoom(request.getRoomId(), houseEntity);

            // Check if the device is already registered
            if (isDeviceRegistered(request.getKickstoneId())) {
                return new DeviceRegisterResponse("Device is already registered", null, HttpStatus.BAD_REQUEST);
            }

            DeviceEntity newDeviceEntity = DeviceEntity.builder()
                    .kickstonId(request.getKickstoneId())
                    .deviceUsername(request.getDeviceUsername())
                    .devicePassword(request.getDevicePassword())
                    .house(houseEntity)
                    .room(roomEntity)
                    .build();

            DeviceEntity savedDeviceEntity = deviceRepository.save(newDeviceEntity);

            DeviceDTOBuilder deviceDTO = convertToDeviceDTO(savedDeviceEntity);

            return new DeviceRegisterResponse("Device registered successfully", JsonUtils.convertObjectToJson(deviceDTO), HttpStatus.OK);

        } catch (Exception e) {
            // Handle other unexpected errors
            e.printStackTrace();
            return new DeviceRegisterResponse("Error registering device: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @EntityGraph(attributePaths = {"house", "room"})
    public AddDeviceResponse addDevice(AddDeviceRequest request) {
        try {

            HouseEntity houseEntity = houseRepository.findById(Long.parseLong(request.getHouseId()))
                    .orElseThrow(() -> new RuntimeException("House not found"));
            // Check if the device is registered
            if (!hasPermissionToAddToInventory(houseEntity)) {
                AddDeviceResponse negResponse = new AddDeviceResponse();
                negResponse.setMessage("User does not have permission to add devices to the inventory");
                negResponse.setHttpStatus(HttpStatus.FORBIDDEN);
                return negResponse;
            }



            RoomEntity roomEntity = roomRepository.findByIdAndHouseId(Long.parseLong(request.getRoomId()), houseEntity.getId())
                    .orElseThrow(() -> new RuntimeException("Room not found within the specific house"));

            DeviceEntity deviceEntity = new DeviceEntity(request.getKickstoneId(), houseEntity, roomEntity);

            deviceRepository.save(deviceEntity);

            AddDeviceResponse response = new AddDeviceResponse();
            response.setMessage("Device was added successfully");
            response.setObject("The added item has the id " + deviceEntity.getKickstonId());
            response.setHttpStatus(HttpStatus.OK);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            AddDeviceResponse negResponse = new AddDeviceResponse();
            negResponse.setMessage("Failed to add device - " + e.getMessage());
            negResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return negResponse;
        }
    }

    public boolean isDeviceRegistered(String kickstoneId) {
        // Check if the device exists in the repository
        return deviceRepository.findByKickstonId(kickstoneId).isPresent();
    }

    private HouseEntity validateAndGetHouse(Long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House not found"));
    }

    private RoomEntity validateAndGetRoom(Long roomId, HouseEntity houseEntity) {
        if (roomId == null) {
            return null;
        }
        return roomRepository.findByIdAndHouseId(roomId, houseEntity.getId())
                .orElseThrow(() -> new RuntimeException("Room with the given id is not found in the specified house"));
    }

    private DeviceDTOBuilder convertToDeviceDTO(DeviceEntity deviceEntity) {
        return DeviceDTOBuilder.builder()
                .id(deviceEntity.getId())
                .kickstonId(deviceEntity.getKickstonId())
                .deviceUsername(deviceEntity.getDeviceUsername())
                .devicePassword(deviceEntity.getDevicePassword())
                .build();
    }

    private boolean hasPermissionToAddToInventory(HouseEntity houseEntity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
