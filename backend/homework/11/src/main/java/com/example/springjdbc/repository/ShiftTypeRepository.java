package com.example.springjdbc.repository;

import com.example.springjdbc.entity.ShiftType;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShiftTypeRepository extends CrudRepository<ShiftType, UUID> {
}
