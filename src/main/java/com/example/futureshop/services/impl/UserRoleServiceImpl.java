package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import com.example.futureshop.repositories.UserRepository;
import com.example.futureshop.repositories.UserRoleRepository;
import com.example.futureshop.services.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;


    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;

        this.userRepository = userRepository;
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRoleEntity> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public Optional<UserRoleEntity> findByRole(UserRoleEnum role) {
        return userRoleRepository.findByRole(role);
    }

    @Override
    public void delete(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public void save(UserRoleEntity roleEntity) {
        userRoleRepository.save(roleEntity);
    }

    @Override
    public void assignUserRole(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        UserRoleEntity role = userRoleRepository.findById(roleId).orElse(null);

        List<UserRoleEntity> userRole = user.getRoles();
        userRole.add(role);
        user.setUserRoles(userRole);

        userRepository.save(user);
    }

    @Override
    public void unassignUserRole(Long userId, Long roleId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        List<UserRoleEntity> userRoles = user.getRoles();

        userRoles.removeIf(x -> x.getId().equals(roleId));

        userRepository.save(user);
    }

    @Override
    public List<UserRoleEntity> getUserRoles(UserEntity user) {
        return user.getRoles();
    }

    @Override
    public List<UserRoleEntity> getUserNotRoles(UserEntity user) {
        return userRoleRepository.getUserNotRoles(user.getId());
    }
}
