package com.example.futureshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "brands")
@Entity
public class BrandEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }
}
