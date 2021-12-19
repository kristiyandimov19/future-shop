package com.example.futureshop.services;

import com.example.futureshop.models.services.RestaurantServiceModel;
import com.example.futureshop.models.views.RestaurantDetailsViewModel;

import java.util.List;

public interface RestaurantService {

    void addRestaurant(RestaurantServiceModel restaurantServiceModel);

    List<RestaurantDetailsViewModel> findAll();

    RestaurantDetailsViewModel findById(Long id);
}
