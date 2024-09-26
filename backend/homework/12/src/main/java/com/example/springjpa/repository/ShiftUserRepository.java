package com.example.springjpa.repository;

import com.example.springjpa.entity.ShiftUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserRepository extends CrudRepository<ShiftUser, UUID> {
    List<ShiftUser> findByTenantId(UUID tenantId);
}
