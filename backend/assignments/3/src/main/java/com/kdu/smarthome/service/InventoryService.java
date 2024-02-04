package com.kdu.smarthome.service;


import com.kdu.smarthome.entity.InventoryEntity;
import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.request.InventoryRequestDTO;
import com.kdu.smarthome.response.InventoryResponse;
import com.kdu.smarthome.response.InventoryResponseDTOForPOST;
import com.kdu.smarthome.utility.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse getInventory()
    {
        List<InventoryEntity> inventoryEntityList = inventoryRepository.findAll();

        String inventoryJson = JsonUtils.convertObjectToJson(inventoryEntityList);

        InventoryResponse response = new InventoryResponse();
        response.setInventory(inventoryJson);
        response.setHttpStatus(HttpStatus.OK);

        return response;
    }


    public InventoryResponseDTOForPOST addItemToInventory(InventoryRequestDTO requestDTO)
    {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .kickstonId(requestDTO.getKickstonId())
                .deviceUsername(requestDTO.getDeviceUsername())
                .devicePassword(requestDTO.getDevicePassword())
                .manufactureDateTime(requestDTO.getManufactureDateTime())
                .manufactureFactoryPlace(requestDTO.getManufactureFactoryPlace())
                .build();

        InventoryEntity savedInventoryEntity = inventoryRepository.save(inventoryEntity);

        return InventoryResponseDTOForPOST.builder()
                .message("Item added to inventory successfully")
                .object(savedInventoryEntity.toString())
                .httpStatus(HttpStatus.valueOf("200 OK"))
                .build();
    }
}
