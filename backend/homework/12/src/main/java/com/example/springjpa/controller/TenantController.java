package com.example.springjpa.controller;

import com.example.springjpa.entity.Tenant;
import com.example.springjpa.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService)
    {
        this.tenantService =tenantService;
    }


    @PostMapping("/tenant/add")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant)
    {
        boolean result = tenantService.addTenant(tenant);
        if(result)
        {
            return ResponseEntity.ok("Tenant was added successfully");
        }
        else {
            return ResponseEntity.ok("Tenant was not added");
        }
    }

}
