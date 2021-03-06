package com.example.futureshop.services;

import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.services.DishServiceModel;
import com.example.futureshop.models.views.DishViewModel;

import java.util.List;

public interface DishService {
    void seedDishes();

    void addDish(DishServiceModel dishServiceModel);

    List<String> findAllDishes();

    DishEntity findByName(String name);

    List<DishEntity> findAll();

    List<DishViewModel> findAllView();

}
