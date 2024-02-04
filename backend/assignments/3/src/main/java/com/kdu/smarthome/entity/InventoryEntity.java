package com.kdu.smarthome.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "inventory")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class InventoryEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime manufactureDateTime;
    private String manufactureFactoryPlace;



}
