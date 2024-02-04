package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.HouseEntity;
import com.kdu.smarthome.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {
    List<HouseEntity> findAllByAdmin(UserEntity admin);

    Optional<Object> findByHouseName(String houseName);
}
