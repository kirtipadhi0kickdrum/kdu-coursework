package com.example.springjdbc.controller;


import com.example.springjdbc.entity.Shift;
import com.example.springjdbc.entity.ShiftType;
import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.entity.User;
import com.example.springjdbc.helper.SaveAllEntitiesRequest;
import com.example.springjdbc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @PostMapping("/shiftTypes/{tenantId}")
    public void saveShiftType(@PathVariable UUID tenantId, @RequestBody ShiftType shiftType) {
        tenantService.saveShiftType(tenantId, shiftType);
    }

    @PostMapping("/{tenantId}/shifts")
    public void saveShift(@PathVariable UUID tenantId, @RequestBody Shift shift) {
        tenantService.saveShift(tenantId, shift);
    }

    @PostMapping("/{tenantId}/users")
    public void saveUser(@PathVariable UUID tenantId, @RequestBody User user) {
        tenantService.saveUser(tenantId, user);
    }

    @PostMapping("/{tenantId}/shiftUsers")
    public void saveShiftUser(@PathVariable UUID tenantId, @RequestBody ShiftUser shiftUser) {
        tenantService.saveShiftUser(tenantId, shiftUser);
    }

    @PostMapping("/{tenantId}/saveAllEntities")
    public void saveAllEntities(@PathVariable UUID tenantId,
                                @RequestBody SaveAllEntitiesRequest request) {
        tenantService.saveAllEntities(tenantId, request);
    }

    @GetMapping("/{tenantId}/shiftTypes")
    public List<ShiftType> getShiftTypesByTenant(@PathVariable UUID tenantId) {
        return tenantService.getShiftTypesByTenant(tenantId);
    }

    @PutMapping("/updateUser/{userId}")
    public void updateUserInformation(@PathVariable UUID userId,
                                      @RequestParam String username) {
        tenantService.updateUserInformation(userId, username);
    }

}
