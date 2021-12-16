package com.example.futureshop.repositories;

import com.example.futureshop.models.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {

    @Query("SELECT d.name FROM DishEntity d")
    List<String> findAllDishes();
}
