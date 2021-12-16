package com.example.futureshop.models.entities;

import com.example.futureshop.models.entities.enums.AllergensEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "allergens")
public class AllergensEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private AllergensEnum allergensEnum;

    public AllergensEnum getAllergensEnum() {
        return allergensEnum;
    }

    public AllergensEntity setAllergensEnum(AllergensEnum allergensEnum) {
        this.allergensEnum = allergensEnum;
        return this;
    }
}
