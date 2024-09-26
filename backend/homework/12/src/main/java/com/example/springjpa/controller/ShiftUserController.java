package com.example.springjpa.controller;


import com.example.springjpa.entity.ShiftUser;
import com.example.springjpa.service.ShiftUserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShiftUserController {

    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService)
    {
        this.shiftUserService = shiftUserService;
    }

    @GetMapping("/shift-user/{tenantId}")
    public ResponseEntity<List<ShiftUser>> getShiftUser(@PathVariable @JsonDeserialize UUID tenantId)
    {
        return ResponseEntity.ok(shiftUserService.getShiftUserByTenantId(tenantId));
    }

    @PostMapping("/shift-user/add")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser)
    {
        boolean result = shiftUserService.addShiftUser(shiftUser);
        if(result)
        {
            return ResponseEntity.ok("Shift user added successfully");
        }
        else{
            return ResponseEntity.ok("Shift user was not added successfully");
        }
    }


    @DeleteMapping("/shift-user/delete/{id}")
    public void deleteShiftUser(@PathVariable UUID id) throws NoSuchFieldException {
        shiftUserService.deleteShiftUser(id);
    }
}
