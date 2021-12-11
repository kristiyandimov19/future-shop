package com.example.futureshop.models.entities;

import com.example.futureshop.models.entities.enums.ModelCategoryEnum;

import javax.persistence.*;

@Table(name = "models")
@Entity
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum category;

    @Column(nullable = false)
    private String brand;

    public String getBrand() {
        return brand;
    }

    public ModelEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(ModelCategoryEnum category) {
        this.category = category;
        return this;
    }


}
