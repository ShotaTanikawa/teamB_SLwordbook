package com.example.slwordbook.controller;


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
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    //ログイン画面に遷移するコントローラー
    @GetMapping()
    public String loginPage() {
        return "login";
    }
    //新規登録画面遷移
    @GetMapping("/signup")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    //user-addで入力した情報を元に、ユーザー情報を登録
    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result,
    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "user-add";
        }
        userService.addDeletedUserAndHashPassword(user);
        return "redirect:/login";
    }
}
