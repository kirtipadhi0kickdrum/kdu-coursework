package com.kdu.smarthome.controller;

import com.kdu.smarthome.response.HouseDetailsResponse;
import com.kdu.smarthome.service.HouseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/house")
public class HouseDetailsController {

    private final HouseDetailsService houseDetailsService;

    @Autowired
    public HouseDetailsController(HouseDetailsService houseDetailsService)
    {
        this.houseDetailsService = houseDetailsService;
    }

    @GetMapping("/{houseId}")
    public HouseDetailsResponse getHouseDetails(@PathVariable Long houseId)
    {
        return houseDetailsService.getHouseDetails(houseId);
    }
}
