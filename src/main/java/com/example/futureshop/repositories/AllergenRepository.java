package com.example.futureshop.repositories;

import com.example.futureshop.models.entities.AllergensEntity;
import com.example.futureshop.models.entities.enums.AllergensEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends JpaRepository<AllergensEntity, Long> {

    @Query("SELECT a.allergensEnum FROM AllergensEntity a")
    List<String> findAllAllergens();

    AllergensEntity findByAllergensEnum(AllergensEnum allergen);
}
