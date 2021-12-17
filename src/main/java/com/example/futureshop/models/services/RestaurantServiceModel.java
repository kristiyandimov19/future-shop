package com.example.futureshop.models.services;

import java.util.List;

public class RestaurantServiceModel {

    private String name;
    private String address;
    private List<String> dishes;

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
