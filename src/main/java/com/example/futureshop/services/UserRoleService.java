package com.example.futureshop.services;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    List<UserRoleEntity> findAll();

    Optional<UserRoleEntity> findById(Long id);

    Optional<UserRoleEntity> findByRole(UserRoleEnum role);

    void delete(Long id);

    void save(UserRoleEntity roleEntity);

    void assignUserRole(Long userId, Long roleId);

    void unassignUserRole(Long userId, Long roleId);

    List<UserRoleEntity> getUserRoles(UserEntity user);

    List<UserRoleEntity> getUserNotRoles(UserEntity user);
}
