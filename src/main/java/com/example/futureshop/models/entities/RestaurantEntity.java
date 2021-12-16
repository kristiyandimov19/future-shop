package com.example.futureshop.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class RestaurantEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String address;
    @OneToMany
    private List<DishEntity> dishes;

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
