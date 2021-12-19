package com.example.futureshop.models.views;

import java.util.List;

public class RestaurantDetailsViewModel {
    private Long id;
    private String name;
    private String address;
    private String imageUrl;
    private List<String> dishes;

    public List<String> getDishes() {
        return dishes;
    }

    public RestaurantDetailsViewModel setDishes(List<String> dishes) {
        this.dishes = dishes;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RestaurantDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantDetailsViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RestaurantDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

}
