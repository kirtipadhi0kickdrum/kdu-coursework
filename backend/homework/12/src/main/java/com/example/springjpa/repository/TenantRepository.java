package com.example.springjpa.repository;

import com.example.springjpa.entity.Tenant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, UUID> {
}
