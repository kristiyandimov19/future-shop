package com.example.futureshop.web;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.models.entities.enums.UserRoleEnum;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.repositories.DishRepository;
import com.example.futureshop.repositories.UserRepository;

import java.math.BigInteger;
import java.util.List;

class DishesTestData {

    private Long testDishId;

    private UserRepository userRepository;
    private AllergenRepository allergenRepository;
    private DishRepository dishRepository;

    public DishesTestData(UserRepository userRepository, AllergenRepository allergenRepository, DishRepository dishRepository) {
        this.userRepository = userRepository;
        this.allergenRepository = allergenRepository;
        this.dishRepository = dishRepository;
    }

    public void init() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.ADMIN);
        UserEntity user = new UserEntity();
        user.setUsername("pesho");
        user.setPassword("password");
        user.setEmail("Email@email.com");
        user = userRepository.save(user);

        AllergensEntity allergensEntity = new AllergensEntity();
        allergensEntity.setAllergensEnum(AllergensEnum.EGGS);
        allergensEntity = allergenRepository.save(allergensEntity);

        DishEntity dish1 = new DishEntity();
        dish1
                .setName("Dish1")
                .setImageUrl("img")
                .setUser(user)
                .setAllergens(List.of(allergensEntity))
                .setProportions(1)
                .setPrice(BigInteger.valueOf(1))
                .setIngredients("ingredients");

        dish1 = dishRepository.save(dish1);

        DishEntity dish2 = new DishEntity();
        dish2
                .setUser(user)
                .setIngredients("ingredients2")
                .setPrice(BigInteger.valueOf(2))
                .setAllergens(List.of(allergensEntity))
                .setImageUrl("img2")
                .setProportions(2)
                .setName("Dish2");

        dish2 = dishRepository.save(dish2);

        testDishId = dish1.getId();
    }

    void cleanUp() {
        dishRepository.deleteAll();
        userRepository.deleteAll();
        allergenRepository.deleteAll();
    }

    public Long getTestDishId() {
        return testDishId;
    }
}
