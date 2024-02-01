package com.example.springjpa.controller;


import com.example.springjpa.entity.Shift;
import com.example.springjpa.service.ShiftService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShiftController {

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService)
    {
        this.shiftService = shiftService;
    }


    @PostMapping("/shifts/add")
    public ResponseEntity<String> addShift(@RequestBody Shift shift)
    {
        try{
            shiftService.addShift(shift);
            return ResponseEntity.ok("Shift added");

        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error - " + e.getMessage());
        }
    }


    @GetMapping("/shifts/get")
    public ResponseEntity<String> getShiftByTenant(@RequestParam @JsonDeserialize UUID tenantId)
    {
        try{
            List<Shift> shift = shiftService.getShift(tenantId);

            return ResponseEntity.ok(shift.toString());

        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error - " + e.getMessage());
        }
    }
}
