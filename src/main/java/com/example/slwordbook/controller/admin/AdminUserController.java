package com.example.slwordbook.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.service.UserService;

@Controller()
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String adminHome() {
        return "admin/home";
    }

    // @GetMapping("/userlist")
    // public String userlist() {

    // }
}
