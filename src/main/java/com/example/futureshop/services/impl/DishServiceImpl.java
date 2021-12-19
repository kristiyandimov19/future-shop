package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.entities.UserEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.models.services.DishServiceModel;
import com.example.futureshop.models.views.DishViewModel;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.repositories.DishRepository;
import com.example.futureshop.services.AllergenService;
import com.example.futureshop.services.DishService;
import com.example.futureshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;
    private final ModelMapper modelMapper;
    private final AllergenService allergenService;
    private final UserService userService;

    public DishServiceImpl(DishRepository dishRepository, AllergenRepository allergenRepository, ModelMapper modelMapper, AllergenService allergenService, UserService userService) {
        this.dishRepository = dishRepository;
        this.allergenRepository = allergenRepository;
        this.modelMapper = modelMapper;
        this.allergenService = allergenService;
        this.userService = userService;
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
                .setImageUrl("https://res.cloudinary.com/dyidaambf/image/upload/v1639683344/shutterstock_1014051751_lialm7.png")
                .setUser(userService.findByUsername("admin"));

        DishEntity dishEntity1 = new DishEntity()
                .setAllergens(List.of(allergen5, allergen7))
                .setName("Mexican Salad")
                .setIngredients("Tomatoes, Peppers, Avocado, Cheese, Onions")
                .setPrice(BigInteger.valueOf(5))
                .setProportions(400)
                .setImageUrl("https://res.cloudinary.com/dyidaambf/image/upload/v1639683377/Tomato-Onion-Salad-Recipe_sn45dr.png")
                .setUser(userService.findByUsername("user"));

        dishRepository.saveAll(List.of(dishEntity1, dishEntity));
    }

    @Override
    public void addDish(DishServiceModel dishServiceModel) {
        DishEntity dishEntity = modelMapper.map(dishServiceModel, DishEntity.class);

        List<AllergensEntity> allergens = new ArrayList<>();

        UserEntity user = userService.findByUsername(dishServiceModel.getUser());

        dishServiceModel.getAllergens()
                .forEach(a -> {
                    allergens.add(allergenService.findByName(a));
                });

        dishEntity.setAllergens(allergens);
        dishEntity.setUser(user);

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

    @Override
    public List<DishEntity> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public List<DishViewModel> findAllView() {
        return dishRepository.findAll()
                .stream()
                .map(de -> {
                    List<String> allergens = new ArrayList<>();
                    DishViewModel dishViewModel = modelMapper.map(de, DishViewModel.class);
                    de.getAllergens().forEach(a -> {
                        if (!allergens.contains(a.getAllergensEnum().name())) {
                            allergens.add(a.getAllergensEnum().name());
                        }
                    });
                    dishViewModel.setAllergens(allergens);
                    return dishViewModel;
                })
                .collect(Collectors.toList());
    }
}
