package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.DeviceEntity;
import com.kdu.smarthome.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByHouse(HouseEntity house);
    Optional<DeviceEntity> findByKickstonId(String kickstonId);

}
