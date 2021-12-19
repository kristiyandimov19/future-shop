package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.RestaurantEntity;
import com.example.futureshop.models.services.RestaurantServiceModel;
import com.example.futureshop.models.views.RestaurantDetailsViewModel;
import com.example.futureshop.repositories.RestaurantRepository;
import com.example.futureshop.services.DishService;
import com.example.futureshop.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<RestaurantDetailsViewModel> findAll() {
        List<String> dishesNames = new ArrayList<>();
        List<RestaurantDetailsViewModel> restaurantDetailsViewModel = restaurantRepository.findAll()
                .stream()
                .map(re -> {
                    RestaurantDetailsViewModel restaurantDetailsViewModel1 = modelMapper
                            .map(re, RestaurantDetailsViewModel.class);
                    re.getDishes().forEach(d -> {
                        if (!dishesNames.contains(d.getName())) {
                            dishesNames.add(d.getName());
                        }
                    });
                    return restaurantDetailsViewModel1;
                }).collect(Collectors.toList());

        restaurantDetailsViewModel.forEach(r -> {
            r.setDishes(dishesNames);
        });
        return restaurantDetailsViewModel;
    }

    @Override
    public RestaurantDetailsViewModel findById(Long id) {

        List<String> dishesNames = new ArrayList<>();

        RestaurantDetailsViewModel restaurantDetailsViewModel = restaurantRepository.findById(id)
                .map(re -> {
                    RestaurantDetailsViewModel restaurantDetailsViewModel1 = modelMapper
                            .map(re, RestaurantDetailsViewModel.class);
                    re.getDishes().forEach(d -> {
                        if (!dishesNames.contains(d.getName())) {
                            dishesNames.add(d.getName());
                        }
                    });
                    return restaurantDetailsViewModel1;
                }).orElseThrow();

        restaurantDetailsViewModel.setDishes(dishesNames);

        return restaurantDetailsViewModel;
    }
}
