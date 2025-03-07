package com.example.slwordbook.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.slwordbook.model.User;
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

    @GetMapping("/userlist")
    public String showUserlist(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<User> users = userService.findAllUser(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "admin/userlist";
    }
}
