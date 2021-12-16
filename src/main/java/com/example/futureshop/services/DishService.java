package com.example.futureshop.services;

import com.example.futureshop.models.services.DishServiceModel;

import java.util.List;

public interface DishService {
    void seedDishes();

    void addDish(DishServiceModel dishServiceModel);

    List<String> findAllDishes();
}
