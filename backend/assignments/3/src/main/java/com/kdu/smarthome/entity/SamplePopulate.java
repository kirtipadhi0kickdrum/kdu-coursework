package com.kdu.smarthome.entity;

import com.kdu.smarthome.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SamplePopulate {

    // created to check the connections and working of the database
    // to be excluded form evaluation point of view

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;


    @Autowired
    public SamplePopulate(InventoryRepository inventoryRepository, UserRepository userRepository, HouseRepository houseRepository
    ,RoomRepository roomRepository,  DeviceRepository deviceRepository)
    {
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;

    }

    @PostConstruct
    public void init(){
        InventoryEntity inventory = InventoryEntity.builder()
                .kickstonId("001")
                .deviceUsername("kirti_01")
                .devicePassword("itachi")
                .manufactureDateTime(LocalDateTime.now())
                .manufactureFactoryPlace("India")
                .build();

        inventoryRepository.save(inventory);


        UserEntity user1 = UserEntity.builder()
                .username("kirti")
                .password("1234")
                .name("kk")
                .firstName("k")
                .lastName("k")
                .emailId("kk@example.com")
                .build();

        userRepository.save(user1);

        UserEntity user2 = UserEntity.builder()
                .username("Aakash")
                .password("12345")
                .name("aa")
                .firstName("a")
                .lastName("a")
                .emailId("aa@example.com")
                .build();

        userRepository.save(user2);

        HouseEntity house = HouseEntity.builder()
                .admin(user1)
                .address("koramanagala")
                .houseName("kirti house")
                .build();

        HouseEntity house2 = HouseEntity.builder()
                        .admin(user1)
                                .address("korara")
                                        .houseName("kirti house 2")
                                                .build();

        houseRepository.save(house);

        houseRepository.save(house2);


        RoomEntity room = RoomEntity.builder()
                .roomName("kirti rrom")
                .build();
        roomRepository.save(room);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .kickstonId("001")
                .deviceUsername("kirti_01")
                .devicePassword("itachi")
                .build();
        deviceRepository.save(deviceEntity);
    }


}
