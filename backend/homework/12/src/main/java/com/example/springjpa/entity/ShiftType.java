package com.example.springjpa.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.service.JavaServiceLoadable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "shift_types")
public class ShiftType {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id")
    private UUID id;
    @Column(name = "uq_name")
    @Enumerated(EnumType.STRING)
    @JsonDeserialize
    private uqEnum name;
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private boolean active;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "time_zone")
    private String timeZone;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public uqEnum getUniqueName()
    {
        return name;
    }

    public enum uqEnum implements Serializable{
        MORNING("morning"), AFTERNOON("afternoon"), NIGHT("night");

        private final String uqName;

        uqEnum(String displayName)
        {
            this.uqName = displayName;
        }

        public String getDisplayName()
        {
            return uqName;
        }
    }


}
