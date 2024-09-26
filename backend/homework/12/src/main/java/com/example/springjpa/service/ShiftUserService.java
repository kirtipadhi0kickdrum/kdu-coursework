package com.example.springjpa.service;


import com.example.springjpa.entity.ShiftUser;
import com.example.springjpa.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShiftUserService {

    ShiftUserRepository shiftUserRepository;

    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository)
    {
        this.shiftUserRepository = shiftUserRepository;
    }

    public boolean addShiftUser(ShiftUser shiftUser)
    {
        try{
            return true;
        }catch (Exception e)
        {
            throw e;
        }
    }

    public List<ShiftUser> getShiftUserByTenantId(UUID tenantId)
    {
        try{
            return shiftUserRepository.findByTenantId(tenantId);
        }catch (Exception e)
        {
            throw e;
        }
    }

    public void deleteShiftUser(UUID shiftUserId) throws NoSuchFieldException {
        Optional<ShiftUser> shiftUserOptional = shiftUserRepository.findById(shiftUserId);
        if (shiftUserOptional.isPresent()) {
            ShiftUser shiftUser = shiftUserOptional.get();
            if (shiftUser.getShift().getTimeEnd().equals(Time.valueOf("23:00:00"))) {
                shiftUserRepository.delete(shiftUser);
            } else {
                throw new IllegalArgumentException("Shift user does not end");
            }
        } else {
            throw new NoSuchFieldException("Shift user not found");
        }
    }
}
