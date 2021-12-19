package com.example.futureshop.models.services;

import java.util.List;

public class RestaurantServiceModel {

    private String name;
    private String address;
    private String imageUrl;
    private List<String> dishes;

    public String getImageUrl() {
        return imageUrl;
    }

    public RestaurantServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public RestaurantServiceModel setDishes(List<String> dishes) {
        this.dishes = dishes;
        return this;
    }
}
