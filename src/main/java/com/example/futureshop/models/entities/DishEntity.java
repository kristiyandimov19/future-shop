package com.example.futureshop.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "dishes")
public class DishEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int proportions;
    @Column(nullable = false)
    private BigInteger price;
    @Column(nullable = false)
    private String ingredients;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;
    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AllergensEntity> allergens;


    public UserEntity getUser() {
        return user;
    }

    public DishEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DishEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public DishEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getProportions() {
        return proportions;
    }

    public DishEntity setProportions(int proportions) {
        this.proportions = proportions;
        return this;
    }

    public BigInteger getPrice() {
        return price;
    }

    public DishEntity setPrice(BigInteger price) {
        this.price = price;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public DishEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public List<AllergensEntity> getAllergens() {
        return allergens;
    }

    public DishEntity setAllergens(List<AllergensEntity> allergens) {
        this.allergens = allergens;
        return this;
    }
}
