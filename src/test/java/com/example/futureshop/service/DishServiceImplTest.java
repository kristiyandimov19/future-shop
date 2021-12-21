package com.example.futureshop.service;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.models.services.DishServiceModel;
import com.example.futureshop.models.views.DishViewModel;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.repositories.DishRepository;
import com.example.futureshop.services.AllergenService;
import com.example.futureshop.services.UserService;
import com.example.futureshop.services.impl.DishServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {

    private UserEntity testUser1, testUser2;
    private DishEntity testDishEntity1, testDishEntity2;
    private AllergensEntity testAllergens1, testAllergens2;
    private DishServiceModel testDishServiceModel;

    private DishServiceImpl serviceToTest;

    @Mock
    DishRepository mockDishRepository;

    @Mock
    UserService mockUserService;

    @Mock
    AllergenRepository mockAllergenRepository;

    @Mock
    AllergenService mockAllergenService;

    @BeforeEach
    public void init() {

        testUser1 = new UserEntity();
        testUser1.setUsername("User1");

        testAllergens1 = new AllergensEntity();
        testAllergens1.setAllergensEnum(AllergensEnum.EGGS);

        List<AllergensEnum> allergensEnum = new ArrayList<>();
        allergensEnum.add(testAllergens1.getAllergensEnum());

        testDishServiceModel = new DishServiceModel();
        testDishServiceModel.setUser("User1")
                .setName("Dish")
                .setAllergens(allergensEnum)
                .setIngredients("ingredients")
                .setPrice(BigInteger.valueOf(1))
                .setProportions(1)
                .setImageUrl("imageUrl");

        testDishEntity1 = new DishEntity();
        testDishEntity1.setName("Dish1")
                .setUser(testUser1)
                .setIngredients("ingredients 1")
                .setImageUrl("imageUrl1")
                .setProportions(1)
                .setAllergens(List.of(testAllergens1))
                .setPrice(BigInteger.valueOf(1));

        testUser2 = new UserEntity();
        testUser2.setUsername("User2");

        testAllergens2 = new AllergensEntity();
        testAllergens2.setAllergensEnum(AllergensEnum.TREE_NUTS);

        testDishEntity2 = new DishEntity();
        testDishEntity2.setName("Dish2")
                .setUser(testUser2)
                .setIngredients("ingredients 2")
                .setImageUrl("imageUrl2")
                .setProportions(2)
                .setAllergens(List.of(testAllergens2))
                .setPrice(BigInteger.valueOf(2));;

        serviceToTest = new DishServiceImpl(mockDishRepository,  mockAllergenRepository, new ModelMapper(), mockAllergenService, mockUserService);
    }

    @Test
    public void testFindAll() {

        when(mockDishRepository.findAll()).thenReturn(List.of(testDishEntity1, testDishEntity2));

        List<DishViewModel> allModels = serviceToTest.findAllView();

        Assertions.assertEquals(2, allModels.size());

        DishViewModel model1 = allModels.get(0);
        DishViewModel model2 = allModels.get(1);

        Assertions.assertEquals(testDishEntity1.getName(), model1.getName());
        Assertions.assertEquals(testDishEntity1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(testDishEntity1.getIngredients(), model1.getIngredients());
        Assertions.assertEquals(testDishEntity1.getAllergens().get(0).getAllergensEnum().name(), model1.getAllergens().get(0));
        Assertions.assertEquals(testDishEntity1.getPrice(), model1.getPrice());
        Assertions.assertEquals(testDishEntity1.getProportions(), model1.getProportions());

        Assertions.assertEquals(testDishEntity2.getName(), model2.getName());
        Assertions.assertEquals(testDishEntity2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(testDishEntity2.getIngredients(), model2.getIngredients());
        Assertions.assertEquals(testDishEntity2.getAllergens().get(0).getAllergensEnum().name(), model2.getAllergens().get(0));
        Assertions.assertEquals(testDishEntity2.getPrice(), model2.getPrice());
        Assertions.assertEquals(testDishEntity2.getProportions(), model2.getProportions());
    }

    @Test
    public void testFindByName() {

        when(mockDishRepository.findByName(testDishEntity1.getName()))
                .thenReturn(testDishEntity1);

        DishEntity dishEntity = serviceToTest.findByName(testDishEntity1.getName());

        Assertions.assertEquals(testDishEntity1, dishEntity);
    }

    @Test
    public void testFindAllEntities() {
        when(mockDishRepository.findAll()).thenReturn(List.of(testDishEntity1, testDishEntity2));

        List<DishEntity> dishes = serviceToTest.findAll();

        Assertions.assertEquals(2, dishes.size());

        DishEntity dish1 = dishes.get(0);
        DishEntity dish2 = dishes.get(1);

        Assertions.assertEquals(testDishEntity1.getName(), dish1.getName());
        Assertions.assertEquals(testDishEntity1.getImageUrl(), dish1.getImageUrl());
        Assertions.assertEquals(testDishEntity1.getIngredients(), dish1.getIngredients());
        Assertions.assertEquals(testDishEntity1.getAllergens(), dish1.getAllergens());
        Assertions.assertEquals(testDishEntity1.getPrice(), dish1.getPrice());
        Assertions.assertEquals(testDishEntity1.getProportions(), dish1.getProportions());

        Assertions.assertEquals(testDishEntity2.getName(), dish2.getName());
        Assertions.assertEquals(testDishEntity2.getImageUrl(), dish2.getImageUrl());
        Assertions.assertEquals(testDishEntity2.getIngredients(), dish2.getIngredients());
        Assertions.assertEquals(testDishEntity2.getAllergens(), dish2.getAllergens());
        Assertions.assertEquals(testDishEntity2.getPrice(), dish2.getPrice());
        Assertions.assertEquals(testDishEntity2.getProportions(), dish2.getProportions());
    }

}
