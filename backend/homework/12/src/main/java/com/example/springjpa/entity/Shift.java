package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "shift_type_id")
    private ShiftType shiftType;
    @Column(name = "name", length = 150)
    private String name;
    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;
    @Column(name = "time_start")
    private Time timeStart;
    @Column(name = "time_end")
    private Time timeEnd;
    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;


    public Time getTimeEnd()
    {
        return timeEnd;
    }


}
