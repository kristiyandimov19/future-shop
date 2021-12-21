package com.example.futureshop.service;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import com.example.futureshop.repositories.UserRepository;
import com.example.futureshop.repositories.UserRoleRepository;
import com.example.futureshop.services.impl.FutureDBUserService;
import com.example.futureshop.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserEntity testUser;

    private UserRoleEntity testUserRole;

    private UserServiceImpl serviceToTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private FutureDBUserService futureDBUserService;

    @BeforeEach
    public void init() {
        testUserRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
        testUser = new UserEntity()
                .setUsername("pesho")
                .setPassword("123")
                .setEmail("email@gmail.com")
                .setUserRoles(List.of(testUserRole));
    }

    @Test
    public void getUserWithCorrectUsername() {
        when(userRepository.findByUsername("pesho"))
                .thenReturn(Optional.of(testUser));

        serviceToTest = new UserServiceImpl(userRoleRepository, userRepository, passwordEncoder, new ModelMapper(), futureDBUserService);

        UserEntity userEntity = serviceToTest.findByUsername("pesho");

        Assertions.assertEquals(testUser.getUsername(), userEntity.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userEntity.getPassword());
        Assertions.assertEquals(testUser.getRoles(), userEntity.getRoles());
        Assertions.assertEquals(testUser.getEmail(), userEntity.getEmail());
    }
}
