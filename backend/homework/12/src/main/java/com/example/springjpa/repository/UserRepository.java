package com.example.springjpa.repository;

import com.example.springjpa.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
    List<User> findByTenantId(UUID tenantId);
    Page<User> findAll(Pageable pageable);
}
