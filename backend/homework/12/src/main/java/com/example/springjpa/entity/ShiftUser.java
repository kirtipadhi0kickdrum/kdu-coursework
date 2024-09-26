package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "shift_users")
public class ShiftUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JoinColumn(name = "shift_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shift;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(name = "tenant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tenant tenant;


    public Shift getShift()
    {
        return shift;
    }

}
