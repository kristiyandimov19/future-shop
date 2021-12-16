package com.example.futureshop.services.impl;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.services.AllergenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergenServiceImpl implements AllergenService {

    private final AllergenRepository allergenRepository;

    public AllergenServiceImpl(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }

    @Override
    public List<String> findAllAllergens() {
        return allergenRepository.findAllAllergens();
    }

    @Override
    public AllergensEntity findByName(AllergensEnum allergen) {
        return allergenRepository.findByAllergensEnum(allergen);
    }
}
