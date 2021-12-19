package com.example.futureshop.web;

import com.example.futureshop.models.binding.DishAddBindingModel;
import com.example.futureshop.models.services.DishServiceModel;
import com.example.futureshop.services.AllergenService;
import com.example.futureshop.services.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final ModelMapper modelMapper;
    private final AllergenService allergenService;
    private final DishService dishService;

    public DishController(ModelMapper modelMapper, AllergenService allergenService, DishService dishService) {
        this.modelMapper = modelMapper;
        this.allergenService = allergenService;
        this.dishService = dishService;
    }

    @ModelAttribute("dishAddBindingModel")
    public DishAddBindingModel createBindingModel() {
        return new DishAddBindingModel();
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("allergens",
                allergenService.findAllAllergens());

        return "add-dish";
    }

    @PostMapping("/add")
    public String addDish(@Valid DishAddBindingModel dishAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("dishAddBindingModel", dishAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.dishAddBindingModel", bindingResult);

            return "redirect:add";
        }

        dishAddBindingModel.setUser(principal.getName());

        DishServiceModel dishServiceModel = modelMapper.map(
                dishAddBindingModel,
                DishServiceModel.class
        );

        dishService.addDish(dishServiceModel);

        return "redirect:/home";
    }

}
