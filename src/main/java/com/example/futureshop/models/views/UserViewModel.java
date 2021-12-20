package com.example.futureshop.models.views;

import com.example.futureshop.models.entities.enums.UserRoleEnum;

import java.util.List;

public class UserViewModel {
    private Long id;
    private String username;
    private List<UserRoleEnum> roles;

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserViewModel setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }
}
