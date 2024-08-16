package com.example.pp313.controller;

import com.example.pp313.model.User;
import com.example.pp313.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserControllers {
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(UserControllers.class.getName());

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String currentUser(ModelMap model) {
        User user = userService.getCurrentUser();
        model.addAttribute("thisUser", user);
        model.addAttribute("flag", user.getUserRolesForUI().contains("ADMIN"));
        logger.info("Получен текущий пользователь: " + user.getUsername() + ", Роли: " + user.getUserRolesForUI());
        return "mainMenu";
    }
}
