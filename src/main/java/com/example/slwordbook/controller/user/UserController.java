package com.example.slwordbook.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    @GetMapping()
    public String adminHome() {
        return "user/home";
    }
}
