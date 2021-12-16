package com.example.futureshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FutureShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FutureShopApplication.class, args);
    }

}
