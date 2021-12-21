package com.example.futureshop.service;

import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.RestaurantEntity;
import com.example.futureshop.models.views.RestaurantDetailsViewModel;
import com.example.futureshop.repositories.RestaurantRepository;
import com.example.futureshop.services.DishService;
import com.example.futureshop.services.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    private DishEntity testDishEntity1, testDishEntity2;
    private RestaurantEntity testRestaurantEntity1, testRestaurantEntity2;

    private RestaurantServiceImpl serviceToTest;

    @Mock
    RestaurantRepository mockRestaurantRepository;

    @Mock
    DishService dishService;

    @BeforeEach
    public void init() {
        testDishEntity1 = new DishEntity();
        testDishEntity1.setName("dish1");

        testDishEntity2 = new DishEntity();
        testDishEntity2.setName("dish2");

        testRestaurantEntity1 = new RestaurantEntity();
        testRestaurantEntity1.setName("Restaurant1")
                .setDishes(List.of(testDishEntity1))
                .setImageUrl("imageUrl1")
                .setAddress("address1");

        testRestaurantEntity2 = new RestaurantEntity();
        testRestaurantEntity2.setName("Restaurant2")
                .setDishes(List.of(testDishEntity2))
                .setImageUrl("imageUrl2")
                .setAddress("address2");

        serviceToTest = new RestaurantServiceImpl(mockRestaurantRepository, new ModelMapper(), dishService);
    }

    @Test
    public void testFindAll() {

        when(mockRestaurantRepository.findAll())
                .thenReturn(List.of(testRestaurantEntity1, testRestaurantEntity2));

        List<RestaurantDetailsViewModel> restaurantEntities = serviceToTest.findAll();

        Assertions.assertEquals(2, restaurantEntities.size());

        RestaurantDetailsViewModel model1 = restaurantEntities.get(0);
        RestaurantDetailsViewModel model2 = restaurantEntities.get(1);

        Assertions.assertEquals(testRestaurantEntity1.getName(), model1.getName());
        Assertions.assertEquals(testRestaurantEntity1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(testRestaurantEntity1.getAddress(), model1.getAddress());
        Assertions.assertEquals(testRestaurantEntity1.getDishes().get(0).getName(), model1.getDishes().get(0));

        Assertions.assertEquals(testRestaurantEntity2.getName(), model2.getName());
        Assertions.assertEquals(testRestaurantEntity2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(testRestaurantEntity2.getAddress(), model2.getAddress());
        Assertions.assertEquals(testRestaurantEntity2.getDishes().get(0).getName(), model2.getDishes().get(1));
    }
}
