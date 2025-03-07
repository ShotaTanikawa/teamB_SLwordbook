package com.example.slwordbook.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.slwordbook.model.Role;
import com.example.slwordbook.model.User;
import com.example.slwordbook.service.UserService;

//ログイン成功時の遷移先を決めるコントローラー
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    //ログイン成功時の遷移先を決めるコントローラー
    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        String username = currentUser.getUsername();
        model.addAttribute("username", username);
        User user = userService.findByname(username);
        if (user == null) {
            return "home";
        }
        Set<Role> roles = user.getRoles();
        String rolename = "";

        for(Role role : roles) {
            rolename = role.getName();
        }
        if (rolename.equals("ROLE_ADMIN")) {
            return "admin/home";
        } else if (rolename.equals("ROLE_USER")) {
            return "user/home";
        } else {
            return "home";
        }
    }

    //localhost:8080/に誤って遷移したときのページ
    //homepage()とまったく同じページに遷移
    @GetMapping
    public String homePage2(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        String username = currentUser.getUsername();
        model.addAttribute("username", username);
        User user = userService.findByname(username);
        if (user == null) {
            return "home";
        }
        Set<Role> roles = user.getRoles();
        String rolename = "";

        for(Role role : roles) {
            rolename = role.getName();
        }
        if (rolename.equals("ROLE_ADMIN")) {
            return "admin/home";
        } else if (rolename.equals("ROLE_USER")) {
            return "user/home";
        } else {
            return "home";
        }
    }
}
