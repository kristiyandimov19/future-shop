package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import com.example.futureshop.models.services.UserRegisterServiceModel;
import com.example.futureshop.models.views.UserViewModel;
import com.example.futureshop.repositories.UserRepository;
import com.example.futureshop.repositories.UserRoleRepository;
import com.example.futureshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final FutureDBUserService futureDBUserService;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, FutureDBUserService futureDBUserService, PasswordEncoder encoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.futureDBUserService = futureDBUserService;
        this.encoder = encoder;
    }

    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

            userRoleRepository.saveAll(List.of(adminRole, userRole, moderatorRole));

            UserEntity admin = new UserEntity()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setEmail("admin@email.com");
            UserEntity user = new UserEntity()
                    .setUsername("user")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setEmail("user@email.com");
            admin.setUserRoles(List.of(adminRole, userRole, moderatorRole));
            user.setUserRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));

        }
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel serviceModel) {
        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository
                .findByRole(UserRoleEnum.USER)
                .orElseThrow(
                        () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        userRepository.save(newUser);

        UserDetails principal = futureDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        newUser.getPassword(),
                        principal.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow();
    }

    @Override
    public List<UserViewModel> findAll() {
        return userRepository.findAll()
                .stream()
                .map(ue -> {
                    List<UserRoleEnum> roles = new ArrayList<>();
                    UserViewModel userViewModel = modelMapper.map(ue, UserViewModel.class);
                    ue.getRoles().forEach(r -> {
                        roles.add(r.getRole());
                    });
                    userViewModel.setRoles(roles);
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserViewModel findById(Long id) {
        return userRepository.findById(id)
                .map(ue -> {
                    UserViewModel userViewModel = modelMapper.map(ue, UserViewModel.class);
                    List<UserRoleEnum> roles = new ArrayList<>();
                    ue.getRoles().forEach(r -> {
                        roles.add(r.getRole());
                    });
                    userViewModel.setRoles(roles);
                    return userViewModel;
                })
                .orElseThrow();
    }


}
