package com.example.futureshop.models.services;

import com.example.futureshop.models.entities.enums.AllergensEnum;

import java.math.BigInteger;
import java.util.List;

public class DishServiceModel {

    private String name;
    private int proportions;
    private BigInteger price;
    private String ingredients;
    private List<AllergensEnum> allergens;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public DishServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getProportions() {
        return proportions;
    }

    public DishServiceModel setProportions(int proportions) {
        this.proportions = proportions;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public DishServiceModel setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public DishServiceModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<AllergensEnum> getAllergens() {
        return allergens;
    }

    public DishServiceModel setAllergens(List<AllergensEnum> allergens) {
        this.allergens = allergens;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DishServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
