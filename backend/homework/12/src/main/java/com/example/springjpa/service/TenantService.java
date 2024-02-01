package com.example.springjpa.service;

import com.example.springjpa.entity.Tenant;
import com.example.springjpa.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Slf4j
@Service
public class TenantService {

    private static final Logger logger = Logger.getLogger(TenantService.class.getName());

    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository)
    {
        this.tenantRepository = tenantRepository;
    }

    public boolean addTenant(Tenant tenant)
    {
        try{
            tenantRepository.save(tenant);
            return true;
        }catch(Exception e)
        {
            logger.info(e.getMessage());
            return false;
        }
    }
}
