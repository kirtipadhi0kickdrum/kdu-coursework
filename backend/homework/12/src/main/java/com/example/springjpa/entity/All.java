package com.example.springjpa.entity;

import lombok.Data;

@Data
public class All {

    Tenant tenant;
    ShiftUser shiftUser;
    Shift shift;
    ShiftType shiftType;
    User user;
}
