package com.example.springjpa.service;


import com.example.springjpa.entity.Shift;
import com.example.springjpa.repository.ShiftRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ShiftService {

    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository)
    {
        this.shiftRepository= shiftRepository;
    }

    public boolean addShift(Shift shift)

    {
        try{
            return true;
        }catch (Exception e)
        {

            return false;
        }
    }


    public List<Shift> getShift(UUID tenantId)
    {
        return shiftRepository.findByTenantId(tenantId);
    }
}
