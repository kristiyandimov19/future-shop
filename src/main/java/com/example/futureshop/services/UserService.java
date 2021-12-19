package com.example.futureshop.services;

import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.services.UserRegisterServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegisterServiceModel serviceModel);

    boolean usernameExists(String username);

    UserEntity findByUsername(String username);

}
