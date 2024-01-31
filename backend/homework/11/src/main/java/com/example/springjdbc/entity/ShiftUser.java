package com.example.springjdbc.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(schema = "shift_users")
public class ShiftUser {
    @Id
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getShiftId() {
        return shiftId;
    }

    public void setShiftId(UUID shiftId) {
        this.shiftId = shiftId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }
}
