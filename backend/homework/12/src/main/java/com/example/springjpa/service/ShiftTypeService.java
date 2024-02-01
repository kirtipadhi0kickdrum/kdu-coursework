package com.example.springjpa.service;


import com.example.springjpa.entity.Shift;
import com.example.springjpa.entity.ShiftType;
import com.example.springjpa.repository.ShiftRepository;
import com.example.springjpa.repository.ShiftTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ShiftTypeService {

    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository)
    {
        this.shiftTypeRepository= shiftTypeRepository;
    }

    public boolean addShiftType(ShiftType shiftType)
    {
        try{
            if(!(shiftType.getUniqueName() == ShiftType.uqEnum.MORNING || shiftType.getUniqueName() == ShiftType.uqEnum.AFTERNOON || shiftType.getUniqueName() == ShiftType.uqEnum.NIGHT))
            {
                throw new InvalidPropertyException(Shift.class, "uniqueName", "unique name must be morning, afternoon or night");
            }
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    public List<ShiftType> getShift(UUID tenantId)
    {
        return shiftTypeRepository.findByTenantId(tenantId);
    }

}
