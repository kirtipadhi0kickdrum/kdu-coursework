package com.example.springjdbc.repository;

import com.example.springjdbc.entity.Shift;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShiftRepository extends CrudRepository<Shift, UUID> {
}
