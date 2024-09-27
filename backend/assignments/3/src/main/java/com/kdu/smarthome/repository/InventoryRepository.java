package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

//    Optional<InventoryEntity> findByKickstonIdAndDeviceUsernameAndPassword(String kickstonId, String deviceUsername, String devicePassword);
    List<InventoryEntity> findByKickstonId(String kickstonId);
}
