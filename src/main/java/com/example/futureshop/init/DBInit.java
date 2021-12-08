package com.example.futureshop.init;

import com.example.futureshop.models.entities.BrandEntity;
import com.example.futureshop.repositories.BrandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;

    public DBInit(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        BrandEntity asus = new BrandEntity();
        asus.setName("Asus");

        BrandEntity gigabyte = new BrandEntity();
        gigabyte.setName("Gigabyte");

        brandRepository.saveAll(List.of(asus, gigabyte));

        //TODO: Try to initialize an offer with a init method

    }
}
