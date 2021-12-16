package com.example.futureshop.services;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;

import java.util.List;

public interface AllergenService {

    List<String> findAllAllergens();

    AllergensEntity findByName(AllergensEnum allergen);
}
