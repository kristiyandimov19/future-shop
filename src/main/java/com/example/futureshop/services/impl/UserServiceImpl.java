package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import com.example.futureshop.models.services.UserRegisterServiceModel;
import com.example.futureshop.repositories.UserRepository;
import com.example.futureshop.repositories.UserRoleRepository;
import com.example.futureshop.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setEmail("admin@email.com");
            UserEntity user = new UserEntity()
                    .setUsername("user")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setEmail("user@email.com");
            admin.setUserRoles(List.of(adminRole, userRole));
            user.setUserRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));

        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel serviceModel) {
        throw new UnsupportedOperationException("NOT YET");
        //todo coming soon
    }
}
