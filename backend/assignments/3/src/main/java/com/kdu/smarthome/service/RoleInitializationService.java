package com.kdu.smarthome.service;

import com.kdu.smarthome.entity.RoleEntity;
import com.kdu.smarthome.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleInitializationService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleInitializationService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        initializeRoleIfNotExists(RoleEntity.UserRole.USER);
        initializeRoleIfNotExists(RoleEntity.UserRole.ADMIN);
    }

    private void initializeRoleIfNotExists(RoleEntity.UserRole roleName) {
        if (!roleRepository.findByName(roleName).isPresent()) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(roleName);
            roleRepository.save(roleEntity);
        }
    }
}
