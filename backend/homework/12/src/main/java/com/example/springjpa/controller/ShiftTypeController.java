package com.example.springjpa.controller;


import com.example.springjpa.entity.ShiftType;
import com.example.springjpa.service.ShiftTypeService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShiftTypeController {


    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService)
    {
        this.shiftTypeService = shiftTypeService;
    }

    @GetMapping("/shift-types/{tenantId}")
    public ResponseEntity<List<ShiftType>> getShiftType(@PathVariable @JsonDeserialize UUID tenantId)
    {
        return ResponseEntity.ok(shiftTypeService.getShift(tenantId));
    }

    @PostMapping("/shift-types/add")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftType shiftType)
    {
        boolean result = shiftTypeService.addShiftType(shiftType);
        if(result)
        {
            return ResponseEntity.ok("Shift type added successfully");
        }
        else {
            return ResponseEntity.ok("Shift type was not added successfully");
        }
    }
}
