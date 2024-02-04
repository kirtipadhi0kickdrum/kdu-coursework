package com.kdu.smarthome.service;


import com.kdu.smarthome.dto.HouseDTO;
import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.request.AddHouseRequestDTO;
import com.kdu.smarthome.response.AddHouseResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository)
    {
        this.houseRepository = houseRepository;
    }


    @Transactional
    public AddHouseResponseDTO addHouse(AddHouseRequestDTO request) {
        try {
            HouseEntity houseEntity = HouseEntity.builder()
                    .address(request.getAddress())
                    .houseName(request.getHouseName())
                    .build();

            HouseEntity savedHouse = houseRepository.save(houseEntity);

            return new AddHouseResponseDTO("House added successfully.", mapToHouseDTO(savedHouse));
        } catch (Exception e) {
            return new AddHouseResponseDTO("Error adding house: " + e.getMessage(), null);
        }
    }

    public List<HouseDTO> getAllHouses(){
        List<HouseEntity> houses = houseRepository.findAll();
        return houses.stream()
                .map(this::convertDTO)
                .toList();

    }

    private HouseDTO convertDTO(HouseEntity houseEntity)
    {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setHouseName(houseEntity.getHouseName());
        houseDTO.setAddress(houseEntity.getAddress());
        return houseDTO;
    }


    private HouseDTO mapToHouseDTO(HouseEntity houseEntity)
    {
        return new HouseDTO(houseEntity.getId(), houseEntity.getAddress(), houseEntity.getHouseName());
    }

    public Long getHouseIdByName(String houseName) {
        HouseEntity houseEntity = (HouseEntity) houseRepository.findByHouseName(houseName)
                .orElseThrow(() -> new RuntimeException("House not found by name: " + houseName));
        return houseEntity.getId();
    }
}
