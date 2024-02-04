package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
//    List<RoomEntity> findByHouse(HouseEntity house);

    Optional<RoomEntity> findByIdAndHouse(Long id, HouseEntity house);


    Optional<RoomEntity> findByIdAndHouseId(Long roomId, Long id);
}
