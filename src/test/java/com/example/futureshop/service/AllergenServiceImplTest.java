package com.example.futureshop.service;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import com.example.futureshop.repositories.AllergenRepository;
import com.example.futureshop.services.impl.AllergenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AllergenServiceImplTest {

    private AllergensEntity testAllergens1, testAllergens2;

    private AllergenServiceImpl serviceToTest;

    @Mock
    AllergenRepository mockAllergenRepository;

    @BeforeEach
    public void init() {

        testAllergens1 = new AllergensEntity();
        testAllergens2 = new AllergensEntity();

        testAllergens1.setAllergensEnum(AllergensEnum.FISH);
        testAllergens2.setAllergensEnum(AllergensEnum.EGGS);

        serviceToTest = new AllergenServiceImpl(mockAllergenRepository);
    }

    @Test
    public void testFindAll() {

        when(mockAllergenRepository.findAllAllergens())
                .thenReturn(List.of(testAllergens1.getAllergensEnum().name(), testAllergens2.getAllergensEnum().name()));

        List<String> testAllergenStrings = serviceToTest.findAllAllergens();

        Assertions.assertEquals(2, testAllergenStrings.size());

        String allergen1 = testAllergenStrings.get(0);
        String allergen2 = testAllergenStrings.get(1);

        Assertions.assertEquals(testAllergens1.getAllergensEnum().name(), allergen1);
        Assertions.assertEquals(testAllergens2.getAllergensEnum().name(), allergen2);
    }

    @Test
    public void testFindByName() {

        when(mockAllergenRepository.findByAllergensEnum(testAllergens1.getAllergensEnum()))
                .thenReturn(testAllergens1);

        AllergensEntity allergensEntity = serviceToTest.findByName(testAllergens1.getAllergensEnum());

        Assertions.assertEquals(testAllergens1, allergensEntity);
    }

}
