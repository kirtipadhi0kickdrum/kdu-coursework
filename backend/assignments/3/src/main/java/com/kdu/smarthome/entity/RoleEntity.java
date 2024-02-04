package com.kdu.smarthome.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRole name;

    public enum UserRole{
        USER,
        ADMIN
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
