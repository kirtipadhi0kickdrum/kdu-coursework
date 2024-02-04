package com.kdu.smarthome.controller;


import com.kdu.smarthome.request.AddHouseRequestDTO;
import com.kdu.smarthome.response.AddHouseResponseDTO;
import com.kdu.smarthome.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService)
    {
        this.houseService = houseService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AddHouseResponseDTO> addHouse(@RequestBody AddHouseRequestDTO request) {
        try {

            AddHouseResponseDTO response = houseService.addHouse(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }



    @GetMapping("/{houseName}/id")
    public ResponseEntity<Long> getHouseIdByName(@PathVariable String houseName) {
        try {
            Long houseId = houseService.getHouseIdByName(houseName);
            return new ResponseEntity<>(houseId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
