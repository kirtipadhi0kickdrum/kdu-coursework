package com.example.springjdbc.helper;

import com.example.springjdbc.entity.Shift;
import com.example.springjdbc.entity.ShiftType;
import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.entity.User;

public class SaveAllEntitiesRequest {
    private ShiftType shiftType;
    private Shift shift;
    private User user;
    private ShiftUser shiftUser;

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShiftUser getShiftUser() {
        return shiftUser;
    }

    public void setShiftUser(ShiftUser shiftUser) {
        this.shiftUser = shiftUser;
    }
}
