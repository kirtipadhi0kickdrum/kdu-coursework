package com.kdu.smarthome.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String roomName;

    @ManyToOne
    private HouseEntity house;


    public RoomEntity(String roomName, HouseEntity house)
    {
        this.roomName = roomName;
        this.house = house;
    }
}
