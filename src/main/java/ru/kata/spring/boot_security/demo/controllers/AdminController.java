package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.AdminService;
import ru.kata.spring.boot_security.demo.servises.RegistrationService;
import ru.kata.spring.boot_security.demo.servises.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    private final RegistrationService registrationService;

    private final UserValidator userValidator;


    @Autowired
    public AdminController(AdminService adminService, UserService userService, RegistrationService registrationService, UserValidator userValidator) {
        this.adminService = adminService;
        this.userService = userService;
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/adminPage";
    }

    @GetMapping("/admin/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        registrationService.register(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", adminService.findAllUsers());
        return "admin/users";
    }

    @GetMapping("/admin/user/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "admin/show";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "admin/edit";
    }

    @PatchMapping("/admin/user/{id}/edit")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable int id) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/user/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
