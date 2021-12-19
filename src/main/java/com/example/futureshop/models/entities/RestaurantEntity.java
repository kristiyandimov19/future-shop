package com.example.futureshop.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<DishEntity> dishes;

    public String getImageUrl() {
        return imageUrl;
    }

    public RestaurantEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RestaurantEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<DishEntity> getDishes() {
        return dishes;
    }

    public RestaurantEntity setDishes(List<DishEntity> dishes) {
        this.dishes = dishes;
        return this;
    }
}
