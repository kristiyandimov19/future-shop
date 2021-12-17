package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.RestaurantEntity;
import com.example.futureshop.models.services.RestaurantServiceModel;
import com.example.futureshop.repositories.RestaurantRepository;
import com.example.futureshop.services.DishService;
import com.example.futureshop.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private final DishService dishService;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper, DishService dishService) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.dishService = dishService;
    }

    @Override
    public void addRestaurant(RestaurantServiceModel restaurantServiceModel) {
        RestaurantEntity restaurantEntity = modelMapper.map(restaurantServiceModel, RestaurantEntity.class);

        List<DishEntity> dishes = new ArrayList<>();

        restaurantServiceModel.getDishes()
                .forEach(d -> {
                    dishes.add(dishService.findByName(d));
                });

        restaurantEntity.setDishes(dishes);

        restaurantRepository.save(restaurantEntity);
    }
}
