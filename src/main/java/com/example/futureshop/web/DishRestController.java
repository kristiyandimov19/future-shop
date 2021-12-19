package com.example.futureshop.web;

import com.example.futureshop.models.views.DishViewModel;
import com.example.futureshop.services.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dishesRest")
public class DishRestController {

    private final DishService dishService;

    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<DishViewModel> showDishes() {

        return dishService.findAllView();
    }
}
