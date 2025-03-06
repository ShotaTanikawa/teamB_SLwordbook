package com.example.slwordbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.model.CustomUserDetails;
import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.service.WordbookService;

@Controller
@RequestMapping("user/wordbooks")
public class WordbookController {
    
    @Autowired
    private WordbookService wordbookService;

    //ログインユーザーの単語帳の一覧を取得
    @GetMapping
    public String listUserWordbooks(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        wordbookService.listAll(userDetails.getUser().getId());
        return "wordbooks/wordbooks";
    }

    //Make the new wordbook
    @GetMapping("/new")
    public String newWordbookForm(Model model) {
        //Make a empty wordbook
        Wordbook wordbook = new Wordbook();
        model.addAttribute("wordbook", wordbook);
        return "wordbooks/wordbook_form";
    }
}
