package com.example.pp313.controller;

import com.example.pp313.model.User;
import com.example.pp313.service.RoleService;
import com.example.pp313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;


@Controller
@RequestMapping("/admin")
public class AdminControllers {
    private static final Logger logger = Logger.getLogger(AdminControllers.class.getName());
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("thisUser", user);
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("flag", user.getUserRolesForUI().contains("ADMIN"));
        logger.info("Администратор просматривает список пользователей");
        return "mainMenu";
    }


    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        logger.info("Изменен пользователь: " + user.getUsername());
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("User") User user) {
        userService.add(user);
        logger.info("Добавлен новый пользователь: " + user.getUsername());
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.remove(userService.getById(id));
        logger.info("Удален пользователь с ID: " + id);
        return "redirect:/admin";
    }
}
