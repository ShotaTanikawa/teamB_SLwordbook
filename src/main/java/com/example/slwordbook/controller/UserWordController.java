package com.example.slwordbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.model.Category;
import com.example.slwordbook.service.CategoryService;
import com.example.slwordbook.service.WordService;

@Controller
@RequestMapping("/user/categories")
public class UserWordController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WordService wordService;

    //カテゴリー一覧画面
    @GetMapping
    public String listCategory(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);
        return "user/categories";
    }

    
}
