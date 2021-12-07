package com.example.futureshop.entities;

import com.example.futureshop.entities.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
