package com.example.pp313.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginControllers {
    @GetMapping
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        modelMap.addAttribute("error", error != null);
        return "login";
    }
}
