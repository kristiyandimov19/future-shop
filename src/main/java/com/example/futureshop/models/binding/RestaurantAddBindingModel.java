package com.example.futureshop.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class RestaurantAddBindingModel {

    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 5)
    private String address;
    @NotBlank
    private String imageUrl;
    @NotEmpty
    private List<String> dishes;

    public String getImageUrl() {
        return imageUrl;
    }

    public RestaurantAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantAddBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public RestaurantAddBindingModel setDishes(List<String> dishes) {
        this.dishes = dishes;
        return this;
    }
}
