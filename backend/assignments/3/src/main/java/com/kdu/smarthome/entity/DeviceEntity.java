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
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;

    @ManyToOne
    private HouseEntity house;

    @ManyToOne
    private RoomEntity room;

    public DeviceEntity(String kickstonId, HouseEntity house, RoomEntity room)
    {
        this.kickstonId = kickstonId;
        this.house = house;
        this.room = room;
    }
}
