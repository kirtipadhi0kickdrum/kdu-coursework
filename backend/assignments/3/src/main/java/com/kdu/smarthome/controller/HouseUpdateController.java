package com.kdu.smarthome.controller;


import com.kdu.smarthome.dto.HouseUpdateDTO;
import com.kdu.smarthome.request.HouseUpdateRequest;
import com.kdu.smarthome.response.HouseUpdateResponse;
import com.kdu.smarthome.service.HouseUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house")
public class HouseUpdateController {

    private final HouseUpdateService houseUpdateService;

    @Autowired
    public HouseUpdateController(HouseUpdateService houseUpdateService)
    {
        this.houseUpdateService = houseUpdateService;
    }

    @PutMapping
    public HouseUpdateResponse updateHouseAddress(@RequestParam Long houseId, @RequestBody HouseUpdateRequest houseUpdateRequest)
    {
        HouseUpdateDTO houseUpdateDTO = new HouseUpdateDTO();
        houseUpdateDTO.setNewAddress(houseUpdateRequest.getNewAddress());

        return houseUpdateService.updateHouseAddress(houseId, houseUpdateDTO);
    }

}
