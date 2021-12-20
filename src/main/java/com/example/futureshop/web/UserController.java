package com.example.futureshop.web;

import com.example.futureshop.models.binding.UserRegisterBindingModel;
import com.example.futureshop.models.entities.UserRoleEntity;
import com.example.futureshop.models.services.UserRegisterServiceModel;
import com.example.futureshop.models.views.UserViewModel;
import com.example.futureshop.services.UserRoleService;
import com.example.futureshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRoleService userRoleService;

    public UserController(ModelMapper modelMapper, UserService userService, UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @ModelAttribute("registerBindingModel")
    public UserRegisterBindingModel createBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        List<UserViewModel> users = userService.findAll();

        model.addAttribute("users", users);

        return "all-users";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {

        UserViewModel userViewModel = userService.findById(id);

        List<UserRoleEntity> userRoleEntities = userRoleService
                .getUserNotRoles(userService.findByUsername(userViewModel.getUsername()));

        List<UserRoleEntity> userRoles = userRoleService
                .getUserRoles(userService.findByUsername(userViewModel.getUsername()));

        model.addAttribute("notRoles", userRoleEntities);

        model.addAttribute("roles", userRoles);

        model.addAttribute("userView", userViewModel);

        return "details-user";
    }

    @RequestMapping("/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Long userId,
                             @PathVariable Long roleId) {
        userRoleService.assignUserRole(userId, roleId);
        return "redirect:/users/details/" + userId;
    }

    @RequestMapping("/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Long userId,
                               @PathVariable Long roleId) {
        userRoleService.unassignUserRole(userId, roleId);
        return "redirect:/users/details/" + userId;
    }

    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegisterBindingModel registerBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.usernameExists(registerBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registerBindingModel", registerBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userServiceModel =
                modelMapper.map(registerBindingModel, UserRegisterServiceModel.class);
        userService.registerAndLoginUser(userServiceModel);



        return "redirect:/home";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes attributes) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

}
