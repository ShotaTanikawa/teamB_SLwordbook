package com.example.slwordbook.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping()
    public String showTestPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("username", currentUser.getUsername());
        return "home";
    }
}
