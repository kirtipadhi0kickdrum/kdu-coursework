package com.example.springjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String tenantName;

    public Tenant(UUID id, String tenantName)
    {
        this.id = id;
        this.tenantName = tenantName;
    }

    public Tenant()
    {

    }
}
