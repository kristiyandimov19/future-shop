package com.example.futureshop.init;

import com.example.futureshop.services.DishService;
import com.example.futureshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final DishService dishService;

    public DBInit(UserService userService, DishService dishService) {
        this.userService = userService;
        this.dishService = dishService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.seedUsers();

        dishService.seedDishes();
    }

}
