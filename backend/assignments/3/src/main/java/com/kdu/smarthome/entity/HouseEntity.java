package com.kdu.smarthome.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String houseName;

    @ManyToOne
    private UserEntity admin;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private List<DeviceEntity> devices;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private List<UserEntity> users;

}
