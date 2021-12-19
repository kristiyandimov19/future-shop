package com.example.futureshop.models.views;

import java.math.BigInteger;
import java.util.List;

public class DishViewModel {
    private String name;
    private int proportions;
    private BigInteger price;
    private String ingredients;
    private String imageUrl;
    private List<String> allergens;

    public String getName() {
        return name;
    }

    public DishViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getProportions() {
        return proportions;
    }

    public DishViewModel setProportions(int proportions) {
        this.proportions = proportions;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public DishViewModel setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public DishViewModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DishViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public DishViewModel setAllergens(List<String> allergens) {
        this.allergens = allergens;
        return this;
    }
}
