package com.example.springjdbc.repository;

import com.example.springjdbc.entity.ShiftUser;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShiftUserRepository extends CrudRepository<ShiftUser, UUID> {
}
