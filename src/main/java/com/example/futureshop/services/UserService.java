package com.example.futureshop.services;

import com.example.futureshop.models.services.UserRegisterServiceModel;

public interface UserService {

    void seedUsers();

    void registerAndLoginUser(UserRegisterServiceModel serviceModel);

}
