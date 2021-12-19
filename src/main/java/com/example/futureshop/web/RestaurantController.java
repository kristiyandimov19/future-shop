package com.example.futureshop.web;

import com.example.futureshop.models.binding.RestaurantAddBindingModel;
import com.example.futureshop.models.entities.DishEntity;
import com.example.futureshop.models.services.RestaurantServiceModel;
import com.example.futureshop.models.views.RestaurantDetailsViewModel;
import com.example.futureshop.services.DishService;
import com.example.futureshop.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final DishService dishService;
    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    public RestaurantController(DishService dishService, RestaurantService restaurantService, ModelMapper modelMapper) {
        this.dishService = dishService;
        this.restaurantService = restaurantService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("restaurantAddBindingModel")
    public RestaurantAddBindingModel addBindingModel() {
        return new RestaurantAddBindingModel();
    }

    @GetMapping("/add")
    public ModelAndView add(@AuthenticationPrincipal UserDetails principal,
                            Model model) {

        ModelAndView mav = new ModelAndView("add-restaurant");
        mav.addObject("user", principal);

        model.addAttribute("dishes", dishService.findAllDishes());

        return mav;
    }

    @PostMapping("/add")
    public String add(@Valid RestaurantAddBindingModel restaurantAddBindingModel,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("restaurantAddBindingModel", restaurantAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.restaurantAddBindingModel", bindingResult);

            return "redirect:/restaurants/add";
        }

        RestaurantServiceModel restaurantServiceModel = modelMapper
                .map(restaurantAddBindingModel, RestaurantServiceModel.class);

        restaurantService.addRestaurant(restaurantServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/all")
    public String showRestaurants(Model model) {
        List<RestaurantDetailsViewModel> restaurants = restaurantService.findAll();

        model.addAttribute("restaurants", restaurants);

        return "all-restaurants";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {

        RestaurantDetailsViewModel restaurantDetailsViewModel = restaurantService.findById(id);

        model.addAttribute("restaurant", restaurantDetailsViewModel);
        List<DishEntity> dishes = new ArrayList<>();
        restaurantDetailsViewModel.getDishes().forEach(d -> {
            dishes.add(dishService.findByName(d));
        });
        model.addAttribute("dishes", dishes);

        return "details-restaurant";
    }

}
