package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.models.services.DishServiceModel;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.repositories.DishRepository;
import com.example.futureshop.services.AllergenService;
import com.example.futureshop.services.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;
    private final ModelMapper modelMapper;
    private final AllergenService allergenService;

    public DishServiceImpl(DishRepository dishRepository, AllergenRepository allergenRepository, ModelMapper modelMapper, AllergenService allergenService) {
        this.dishRepository = dishRepository;
        this.allergenRepository = allergenRepository;
        this.modelMapper = modelMapper;
        this.allergenService = allergenService;
    }

    @Override
    public void seedDishes() {

        AllergensEntity allergen1 = new AllergensEntity().setAllergensEnum(AllergensEnum.EGGS);
        AllergensEntity allergen2 = new AllergensEntity().setAllergensEnum(AllergensEnum.MILK);
        AllergensEntity allergen3 = new AllergensEntity().setAllergensEnum(AllergensEnum.WHEAT);
        AllergensEntity allergen4 = new AllergensEntity().setAllergensEnum(AllergensEnum.FISH);
        AllergensEntity allergen5 = new AllergensEntity().setAllergensEnum(AllergensEnum.CRUSTACEAN_SHELLFISH);
        AllergensEntity allergen6 = new AllergensEntity().setAllergensEnum(AllergensEnum.PEANUTS);
        AllergensEntity allergen7 = new AllergensEntity().setAllergensEnum(AllergensEnum.SOYBEANS);
        AllergensEntity allergen8 = new AllergensEntity().setAllergensEnum(AllergensEnum.TREE_NUTS);

        allergenRepository.saveAll(List.of(allergen1, allergen2, allergen3, allergen4, allergen5, allergen6, allergen7, allergen8));

        DishEntity dishEntity = new DishEntity()
                .setAllergens(List.of(allergen1, allergen2, allergen3))
                .setName("Taco")
                .setIngredients("Tortilla, Rice, Beans, Cheese, Minced Steak")
                .setPrice(BigInteger.valueOf(7))
                .setProportions(500)
                .setImageUrl("https://res.cloudinary.com/dyidaambf/image/upload/v1639683344/shutterstock_1014051751_lialm7.png");

        DishEntity dishEntity1 = new DishEntity()
                .setAllergens(List.of(allergen5, allergen7))
                .setName("Mexican Salad")
                .setIngredients("Tomatoes, Peppers, Avocado, Cheese, Onions")
                .setPrice(BigInteger.valueOf(5))
                .setProportions(400)
                .setImageUrl("https://res.cloudinary.com/dyidaambf/image/upload/v1639683377/Tomato-Onion-Salad-Recipe_sn45dr.png");

        dishRepository.saveAll(List.of(dishEntity1, dishEntity));
    }

    @Override
    public void addDish(DishServiceModel dishServiceModel) {
        DishEntity dishEntity = modelMapper.map(dishServiceModel, DishEntity.class);

        List<AllergensEntity> allergens = new ArrayList<>();

        dishServiceModel.getAllergens()
                .forEach(a -> {
                    allergens.add(allergenService.findByName(a));
                });

        dishEntity.setAllergens(allergens);

        dishRepository.save(dishEntity);
    }

    @Override
    public List<String> findAllDishes() {
        return dishRepository.findAllDishes();
    }

    @Override
    public DishEntity findByName(String name) {
        return dishRepository.findByName(name);
    }
}
