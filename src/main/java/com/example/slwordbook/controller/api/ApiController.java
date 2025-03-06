package com.example.slwordbook.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.model.User;
import com.example.slwordbook.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/signup")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result,
    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "user-add";
        }
        userService.addDeletedUserAndHashPassword(user);
        return "redirect:/api";
    }
}
