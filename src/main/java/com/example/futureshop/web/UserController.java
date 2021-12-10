package com.example.futureshop.web;

import com.example.futureshop.models.binding.UserRegisterBindingModel;
import com.example.futureshop.models.services.UserRegisterServiceModel;
import com.example.futureshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLoginUser(UserRegisterBindingModel registerBindingModel) {
        UserRegisterServiceModel userServiceModel =
                modelMapper.map(registerBindingModel, UserRegisterServiceModel.class);
        userService.registerAndLoginUser(userServiceModel);
        //todo validation

        return "redirect:/home";
    }

}
