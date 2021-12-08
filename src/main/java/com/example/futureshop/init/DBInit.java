package com.example.futureshop.init;

import com.example.futureshop.models.entities.BrandEntity;
import com.example.futureshop.repositories.BrandRepository;
import com.example.futureshop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final UserService userService;

    public DBInit(BrandRepository brandRepository, UserService userService) {
        this.brandRepository = brandRepository;

        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        userService.seedUsers();

        BrandEntity asus = new BrandEntity();
        asus.setName("Asus");

        BrandEntity gigabyte = new BrandEntity();
        gigabyte.setName("Gigabyte");

        brandRepository.saveAll(List.of(asus, gigabyte));

        //TODO: Try to initialize an offer with an init method

    }

    private void initUsers() {

    }
}
