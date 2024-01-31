package com.example.springjdbc.service;

import com.example.springjdbc.entity.Shift;
import com.example.springjdbc.entity.ShiftType;
import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.entity.User;
import com.example.springjdbc.helper.SaveAllEntitiesRequest;

import java.util.List;
import java.util.UUID;

public interface TenantService {
    void saveShiftType(UUID tenantId, ShiftType shiftType);
    void saveShift(UUID tenantId, Shift shift);
    void saveUser(UUID tenantId,User user);
    void saveShiftUser(UUID tenantId, ShiftUser shiftUser);

    void saveAllEntities(UUID tenantId, SaveAllEntitiesRequest request);

    List<ShiftType> getShiftTypesByTenant(UUID tenantId);
    List<Shift> getShiftsByTenant(UUID tenantId);
    List<User> getUsersByTenant(UUID tenantId);
    List<ShiftUser> getShiftUsersByTenant(UUID tenantId);

    void updateUserInformation(UUID userId, String username);
}
