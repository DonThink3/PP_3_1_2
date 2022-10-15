package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.servises.RoleService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;



    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        model.addAttribute("admin", userDetails.getUser());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("newUser", new User());
        return "admin/adminPage";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") User user, @RequestParam("roles") Set<Role> roles) {
        userService.save(user,roles);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/user/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable int id, @RequestParam("roles") Set<Role> roles) {
        userService.update(id, user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/user/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
