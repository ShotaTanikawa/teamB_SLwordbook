package com.example.slwordbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.service.WordbookService;

@Controller
@RequestMapping("user/wordbooks")
public class WordbookController {
    
    @Autowired
    private WordbookService wordbookService;

    //Wordbooks list display
    @GetMapping
    public String listWordbooks(Model model) {
        List<Wordbook> listWordbooks = wordbookService.listAll();
        model.addAttribute("listWordbooks", listWordbooks);
        return "wordbooks/wordbooks";
    }
}
