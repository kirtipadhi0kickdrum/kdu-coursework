package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.HouseUpdateDTO;
import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.response.HouseUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseUpdateService {

    private HouseRepository houseRepository;

    @Autowired
    public HouseUpdateService(HouseRepository houseRepository)
    {
        this.houseRepository = houseRepository;
    }

    public HouseUpdateResponse updateHouseAddress(Long houseId, HouseUpdateDTO houseUpdateDTO)
    {
        Optional<HouseEntity> optionalHouse = houseRepository.findById(houseId);

        if(optionalHouse.isPresent())
        {
            HouseEntity houseEntity = optionalHouse.get();
            houseEntity.setAddress(houseUpdateDTO.getNewAddress());
            houseRepository.save(houseEntity);

            return new HouseUpdateResponse("House address updated successfully ", houseEntity.toString(), HttpStatus.OK);

        }
        else {
            return new HouseUpdateResponse("House not found", null, HttpStatus.NOT_FOUND);
        }
    }
}
