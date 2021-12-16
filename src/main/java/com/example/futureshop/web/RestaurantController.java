package com.example.futureshop.web;

import com.example.futureshop.services.DishService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class RestaurantController {

    private final DishService dishService;

    public RestaurantController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/restaurants/add")
    public ModelAndView add(@AuthenticationPrincipal UserDetails principal,
                            Model model) {

        ModelAndView mav = new ModelAndView("add-restaurant");
        mav.addObject("user", principal);

        model.addAttribute("dishes", dishService.findAllDishes());

        return mav;
    }
}
