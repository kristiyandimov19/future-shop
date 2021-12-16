package com.example.futureshop.models.binding;

import com.example.futureshop.models.entities.enums.AllergensEnum;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.List;

public class DishAddBindingModel {

    @NotBlank
    @Size(min = 3)
    private String name;
    @Min(0)
    private int proportions;
    @DecimalMin("0")
    @NotNull
    private BigInteger price;
    @NotBlank
    private String ingredients;
    @NotNull
    private List<AllergensEnum> allergens;
    @NotBlank
    private String imageUrl;

    public String getName() {
        return name;
    }

    public DishAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public int getProportions() {
        return proportions;
    }

    public DishAddBindingModel setProportions(int proportions) {
        this.proportions = proportions;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public DishAddBindingModel setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public DishAddBindingModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<AllergensEnum> getAllergens() {
        return allergens;
    }

    public DishAddBindingModel setAllergens(List<AllergensEnum> allergens) {
        this.allergens = allergens;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DishAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
