package com.example.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "loggedin")
    private int loggedIn;
    @Column(name = "time_zone")
    private String timeZone;


    @JoinColumn(name = "tenant_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenant tenant;


    public User(String username, String timeZone, Tenant tenant)
    {
        this.username = username;
        this.timeZone = timeZone;
        this.tenant = tenant;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}
